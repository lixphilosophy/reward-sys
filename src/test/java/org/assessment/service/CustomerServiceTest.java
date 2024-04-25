package org.assessment.service;

import org.assessment.domain.customer.CustomerDto;
import org.assessment.domain.request.AddCustomerRequest;
import org.assessment.entity.Customer;
import org.assessment.repository.CustomerRepository;
import org.assessment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testAddCustomer() {
        // Given
        AddCustomerRequest request = new AddCustomerRequest("John", "Doe");
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        String customerId = customerService.addCustomer(request);

        // Then
        assertNotNull(customerId);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testGetAllCustomers() {
        // Given
        List<Customer> mockCustomers = Arrays.asList(
                new Customer("id1", "John", "Doe"),
                new Customer("id2", "Jane", "Doe")
        );
        when(customerRepository.findAll()).thenReturn(mockCustomers);

        // When
        List<CustomerDto> customerDtos = customerService.getAllCustomers();

        // Then
        assertEquals(2, customerDtos.size());
        assertEquals("John", customerDtos.get(0).getCustomerFirstName());
        assertEquals("Jane", customerDtos.get(1).getCustomerFirstName());
        verify(customerRepository).findAll();
    }
}
