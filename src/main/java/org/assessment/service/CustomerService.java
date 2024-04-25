package org.assessment.service;

import org.assessment.domain.customer.CustomerDto;
import org.assessment.domain.request.AddCustomerRequest;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    String addCustomer(AddCustomerRequest request);
}
