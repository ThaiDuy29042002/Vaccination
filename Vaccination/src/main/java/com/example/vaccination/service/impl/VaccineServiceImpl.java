package com.example.vaccination.service.impl;

import com.example.vaccination.controller.Helper;
import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class VaccineServiceImpl {

    @Autowired
    private VaccineRepository repository;

    @Autowired
    private Helper helper;

    public List<Vaccine> getAllProducts() {
        return this.repository.findAll();
    }

    public Vaccine addNew(Vaccine p) {
        return repository.save(p);
    }

    public Vaccine findById(String id) {
        try {
            return repository.findById(id).orElse(null);
        } catch (NotFoundException ex) {
            throw ex;
        }
    }

    public void saveByExcel(MultipartFile file) {
        try {
            List<Vaccine> products = helper.convertExcelToListOfProduct(file.getInputStream());
            this.repository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Vaccine> findByTimeBeginNextInjection(Date from){

        return repository.search(from);
    }

    public List<Vaccine> getVaccinesByDate(String from) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(from);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle this exception in a manner that makes sense for your application.
        }
        return repository.findByTimeBeginNextInjection(date);
    }






}