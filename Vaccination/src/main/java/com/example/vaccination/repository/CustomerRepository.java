package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //Query for transient
    @Query(value = "SELECT COUNT(r.injection_result_id) FROM injection_results r " +
            "WHERE r.customer_id = :customerId", nativeQuery = true)
    Long getInjectionCountByCustomerId(@Param("customerId") Long customerId);

    //Query by input conditions
    @Query("SELECT c FROM Customer c WHERE " +
            "(:from IS NULL OR Date(c.dateOfBirth) >= :from) AND " +
            "(:to IS NULL OR Date(c.dateOfBirth) <= :to) AND " +
            "(:fullName IS NULL OR c.fullName LIKE %:fullName%) AND " +
            "(:address IS NULL OR c.address LIKE %:address%)")
    List<Customer> customeFilter(Date from, Date to, String fullName, String address);



}
