package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.Customer;
import com.example.vaccination.repository.CustomerRepository;
import com.example.vaccination.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
