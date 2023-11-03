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


    public List<Vaccine> findbyoptional2(String vaccineSelection, String origin, Date from, Date to) {

        // Nếu tất cả tham số đều được cung cấp
        if (vaccineSelection != null && !vaccineSelection.isEmpty() &&
                origin != null && !origin.isEmpty() &&
                from != null && to != null) {
            return repository.findAllByCriteria(from, to, vaccineSelection, origin);
        }

        // Kiểm tra Date from và to
        else if (from != null || to != null) {
            return repository.findAllByTimeBeginNextInjectionOrTimeEndNextInjection(from, to);
        }

        // Kiểm tra origin
        else if (origin != null && !origin.isEmpty()) {
            return repository.findAllByOriginContains(origin);
        }

        // Kiểm tra vaccineSelection
        else if (vaccineSelection != null && !vaccineSelection.isEmpty()) {
            return repository.findAllByVaccineID(vaccineSelection);
        }

        // Nếu không tham số nào rõ ràng, trả về tất cả sản phẩm
        else {
            return getAllProducts();
        }
    }


    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.repository = vaccineRepository;
    }

    //Truy vấn dữ liệu theo năm
    public List<String> count(String yearSelect){
        return repository.CountVaccine(yearSelect);
    }


}