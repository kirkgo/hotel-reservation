package tech.kirk.hotelreservation.service;

import tech.kirk.hotelreservation.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerServiceInstance;
    private Map<String, Customer> customers;

    public CustomerService() {
        customers = new HashMap<String, Customer>();
    }

    public static CustomerService getInstance() {
        if (customerServiceInstance == null) {
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
