package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findAll();

}