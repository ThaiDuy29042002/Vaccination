package com.example.vaccination.service;

import com.example.vaccination.model.entity.InjectionResult;

import java.util.List;

public interface InjectionResultService {
    InjectionResult addInjectionResult(InjectionResult result);
    InjectionResult updateInjectionResult(int injectionResultID, InjectionResult updateInjection);

    InjectionResult getInjectionResultbyID(int injectionResultID);

    List<InjectionResult> getALLInjectionResult();

    void deleteInjectionResultById(int injectionResultID);

    List<InjectionResult> findAll();


}
