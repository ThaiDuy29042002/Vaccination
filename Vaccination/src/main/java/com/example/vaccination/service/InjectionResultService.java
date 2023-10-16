package com.example.vaccination.service;

import com.example.vaccination.model.entity.InjectionResult;

import java.util.List;

public interface InjectionResultService {
    InjectionResult addInjectionResult(InjectionResult result);
    InjectionResult updateInjectionResult(int id, InjectionResult updateInjection);

    InjectionResult getInjectionResultbyID(int id);

    List<InjectionResult> getALLInjectionResult();

    void deleteInjectionResultById(int id);

    List<InjectionResult> findAll();
}
