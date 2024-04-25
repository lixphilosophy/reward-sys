package org.assessment.controller;

import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.request.AddTransactionRequest;
import org.assessment.domain.request.BatchAddTransactionRequest;
import org.assessment.exception.transaction.InvalidRequestException;
import org.assessment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class TransactionControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private AddTransactionRequest validRequest;

    @BeforeEach
    void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
        mockMvc = standaloneSetup(transactionController).build();

        validRequest = new AddTransactionRequest("C001", 100, ZonedDateTime.now());
    }

    @Test
    public void testBatchAddRecord() throws Exception {
        List<String> transactionIds = Arrays.asList("TX1", "TX2");

        when(transactionService.batchAddTransaction(any(BatchAddTransactionRequest.class))).thenReturn(transactionIds);

        mockMvc.perform(post("/transaction/api/v1/batch/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"transactions\":[{\"customerId\":\"C123\",\"amount\":100, \"transactionTime\": \"2024-04-23T00:00:00.000Z\"}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(StatusCode.OK))
                .andExpect(jsonPath("$.msg").value(StatusMsg.NO_ERROR))
                .andExpect(jsonPath("$.data.ent.transactionIds[0]").value("TX1"));

        verify(transactionService).batchAddTransaction(any(BatchAddTransactionRequest.class));
    }

    @Test
    public void testAddRecord() throws Exception {
        when(transactionService.addTransaction(any(AddTransactionRequest.class))).thenReturn("TX102");

        mockMvc.perform(post("/transaction/api/v1/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":\"C001\",\"amount\":100,\"transactionTime\":\"2024-04-12T18:36:31.890+00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(StatusCode.OK))
                .andExpect(jsonPath("$.msg").value(StatusMsg.NO_ERROR))
                .andExpect(jsonPath("$.data.ent.transactionId").value("TX102"));

        verify(transactionService, times(1)).addTransaction(any(AddTransactionRequest.class));
    }

    @Test
    void requestCheckHelper_AllValid_ShouldNotThrowException() {
        assertDoesNotThrow(() -> transactionController.requestCheckHelper(validRequest));
    }

    @Test
    void requestCheckHelper_NullCustomerId_ShouldThrowException() {
        validRequest.setCustomerId(null);
        Exception exception = assertThrows(InvalidRequestException.class,
                () -> transactionController.requestCheckHelper(validRequest));
        assertEquals("customerId is required", exception.getMessage());
    }

    @Test
    void requestCheckHelper_NullAmount_ShouldThrowException() {
        validRequest.setAmount(null);
        Exception exception = assertThrows(InvalidRequestException.class,
                () -> transactionController.requestCheckHelper(validRequest));
        assertEquals("please provide amount customer spent", exception.getMessage());
    }

    @Test
    void requestCheckHelper_NullTransactionTime_ShouldThrowException() {
        validRequest.setTransactionTime(null);
        Exception exception = assertThrows(InvalidRequestException.class,
                () -> transactionController.requestCheckHelper(validRequest));
        assertEquals("please provide transaction time", exception.getMessage());
    }
}