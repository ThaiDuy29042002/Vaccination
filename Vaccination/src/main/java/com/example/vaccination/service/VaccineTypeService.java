package com.example.vaccination.service;

import com.example.vaccination.model.entity.VaccineType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface VaccineTypeService {
    List<VaccineType> findAll();
    VaccineType save(VaccineType vaccineType);

    VaccineType findById(String id);
    VaccineType findByVaccineTypeName(String name);
    public void save1 (MultipartFile file);
    public Resource load(String filename);
    public Stream<Path> loadAll();
    public void init();

}
