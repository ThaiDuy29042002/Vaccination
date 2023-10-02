package com.example.vaccine.repository;

import com.example.vaccine.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
