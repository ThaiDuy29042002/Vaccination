package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.InjectionResult;
import com.example.vaccination.repository.InjectionResultRepository;
import com.example.vaccination.service.InjectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InjectionResultServiceImpl implements InjectionResultService {
    @Autowired
    InjectionResultRepository injectionResultRepository;

    @Override
    public InjectionResult addInjectionResult(InjectionResult result){
        return injectionResultRepository.save(result);
    }

    @Override
    public InjectionResult getInjectionResultbyID(int injectionResultID){
        // Add or update new result
        return injectionResultRepository.findById(injectionResultID).orElse(null);
    }
    @Override
    public List<InjectionResult>getALLInjectionResult(){
        // show all result
        return injectionResultRepository.findAll();
    }
    @Override
    public void deleteInjectionResultById(int injectionResultID){
        // delete result follow id
        InjectionResult injectionResult = injectionResultRepository.findById(injectionResultID).orElse(null);
        if (injectionResult !=null){
            injectionResult.setStatus(false);
            injectionResultRepository.save(injectionResult);
        }
    }

    @Override
    public List<InjectionResult> findAll() {
        return injectionResultRepository.findAll();
    }

    @Override
    public InjectionResult updateInjectionResult(int injectionResultID, InjectionResult updatedResult){
        InjectionResult oldInjection = injectionResultRepository.findByInjectionResultID(injectionResultID);
        injectionResultRepository.saveAndFlush(updatedResult);
        return injectionResultRepository.save(updatedResult);
    }

}