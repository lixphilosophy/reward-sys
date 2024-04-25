package org.assessment.controller;

import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.customer.CustomerDto;
import org.assessment.domain.request.AddCustomerRequest;
import org.assessment.exception.customer.CustomerRequestException;
import org.assessment.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class CustomerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private AddCustomerRequest validRequest;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);
        mockMvc = standaloneSetup(customerController).build();

        validRequest = new AddCustomerRequest("John", "Doe");
    }

    @Test
    public void testAddCustomer_ValidRequest() throws Exception {
        AddCustomerRequest request = new AddCustomerRequest("John", "Doe");
        when(customerService.addCustomer(any(AddCustomerRequest.class))).thenReturn("cust123");

        mockMvc.perform(post("/customer/api/v1/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerFirstName\":\"John\", \"customerLastName\":\"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(StatusCode.OK))
                .andExpect(jsonPath("$.msg").value(StatusMsg.NO_ERROR))
                .andExpect(jsonPath("$.data.ent.customerId").value("cust123"));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<CustomerDto> customers = Arrays.asList(new CustomerDto("123", "John", "Doe"));
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customer/api/v1/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(StatusCode.OK))
                .andExpect(jsonPath("$.msg").value(StatusMsg.NO_ERROR))
                .andExpect(jsonPath("$.data.ent.customers[0].customerId").value("123"))
                .andExpect(jsonPath("$.data.ent.customers[0].customerFirstName").value("John"))
                .andExpect(jsonPath("$.data.ent.customers[0].customerLastName").value("Doe"));
    }

    @Test
    void requestCheckHelper_NullFN_ShouldThrowException() {
        validRequest.setCustomerFirstName(null);
        Exception exception = assertThrows(CustomerRequestException.class,
                () -> customerController.requestCheckHelper(validRequest));
        assertEquals("Please provide customer firstname and lastname", exception.getMessage());
    }

    @Test
    void requestCheckHelper_NullLN_ShouldThrowException() {
        validRequest.setCustomerLastName(null);
        Exception exception = assertThrows(CustomerRequestException.class,
                () -> customerController.requestCheckHelper(validRequest));
        assertEquals("Please provide customer firstname and lastname", exception.getMessage());
    }
}
