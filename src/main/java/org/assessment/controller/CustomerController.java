package org.assessment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.constant.StatusCode;
import org.assessment.constant.StatusMsg;
import org.assessment.domain.customer.CustomerDto;
import org.assessment.domain.request.AddCustomerRequest;
import org.assessment.domain.response.AddCustomerResponse;
import org.assessment.domain.response.GetAllCustomerResponse;
import org.assessment.domain.response.ResponseDTO;
import org.assessment.exception.customer.CustomerRequestException;
import org.assessment.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customer/api/v1")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<AddCustomerResponse, Object>> addCustomer(
            @RequestBody AddCustomerRequest request) {
        log.info("Adding customer: {}", request);

        requestCheckHelper(request);

        String customerId = customerService.addCustomer(request);

        return ResponseEntity.ok().body(ResponseDTO.<AddCustomerResponse, Object>builder()
                .code(StatusCode.OK)
                .msg(StatusMsg.NO_ERROR)
                .data(ResponseDTO.Data.<AddCustomerResponse, Object>builder()
                        .ent(AddCustomerResponse.builder()
                                .customerId(customerId)
                                .build())
                        .build())
                .build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO<GetAllCustomerResponse, Object>> getAllCustomers() {
        log.info("Getting all customers");

        List<CustomerDto> customers = customerService.getAllCustomers();

        return ResponseEntity.ok().body(ResponseDTO.<GetAllCustomerResponse, Object>builder()
                .code(StatusCode.OK)
                .msg(StatusMsg.NO_ERROR)
                .data(ResponseDTO.Data.<GetAllCustomerResponse, Object>builder()
                        .ent(GetAllCustomerResponse.builder()
                                .customers(customers)
                                .build())
                        .build())
                .build());
    }

    protected void requestCheckHelper(AddCustomerRequest request) {
        if (Objects.isNull(request.getCustomerFirstName()) || Objects.isNull(request.getCustomerLastName())) {
            throw new CustomerRequestException("Please provide customer firstname and lastname");
        }
        // add more request checks here later if needed
    }
}
