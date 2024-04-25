package org.assessment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testCustomerGettersAndSetters() {
        Customer customer = new Customer();
        customer.setCustomerId("C123");
        customer.setFirstName("John");
        customer.setLastName("Doe");

        assertEquals("C123", customer.getCustomerId(), "Customer ID should match the set value.");
        assertEquals("John", customer.getFirstName(), "First name should match the set value.");
        assertEquals("Doe", customer.getLastName(), "Last name should match the set value.");
    }

    @Test
    public void testCustomerAllArgsConstructor() {
        Customer customer = new Customer("C123", "John", "Doe");

        assertEquals("C123", customer.getCustomerId(), "Customer ID should match the constructor argument.");
        assertEquals("John", customer.getFirstName(), "First name should match the constructor argument.");
        assertEquals("Doe", customer.getLastName(), "Last name should match the constructor argument.");
    }

    @Test
    public void testCustomerBuilder() {
        Customer customer = Customer.builder()
                .customerId("C123")
                .firstName("John")
                .lastName("Doe")
                .build();

        assertEquals("C123", customer.getCustomerId(), "Customer ID should match the builder value.");
        assertEquals("John", customer.getFirstName(), "First name should match the builder value.");
        assertEquals("Doe", customer.getLastName(), "Last name should match the builder value.");
    }

}
