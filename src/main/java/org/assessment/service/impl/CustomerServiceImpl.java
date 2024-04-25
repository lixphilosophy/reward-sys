package org.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assessment.domain.customer.CustomerDto;
import org.assessment.domain.request.AddCustomerRequest;
import org.assessment.entity.Customer;
import org.assessment.repository.CustomerRepository;
import org.assessment.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public String addCustomer(AddCustomerRequest request) {
        String customerId = UUID.randomUUID().toString();
        Customer customer = Customer.builder()
                .customerId(customerId)
                .firstName(request.getCustomerFirstName())
                .lastName(request.getCustomerLastName())
                .build();
        customerRepository.save(customer);
        return customerId;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers.stream().map(customer -> CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .customerFirstName(customer.getFirstName())
                .customerLastName(customer.getLastName())
                .build()).collect(Collectors.toList());
    }
}
