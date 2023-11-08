package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {
    private final Path root = Paths.get("./uploads");

    @Autowired
    VaccineTypeRepository vaccineTypeRepository;
    @Override
    public List<VaccineType> findAll() {
        return vaccineTypeRepository.findAll();
    }

    @Override
    public VaccineType save(VaccineType vaccineType) {
        return vaccineTypeRepository.saveAndFlush(vaccineType);
    }

    @Override
    public VaccineType findById(String id) {
        return vaccineTypeRepository.findById(id).orElse(null);
    }

    @Override
    public VaccineType findByVaccineTypeName(String name) {
        return vaccineTypeRepository.findByVaccineTypeName(name).orElse(null);
    }

}
