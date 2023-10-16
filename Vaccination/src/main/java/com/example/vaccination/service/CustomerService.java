package com.example.vaccination.service;

import com.example.vaccination.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
}
