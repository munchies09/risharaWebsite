package com.csc3402.security.formlogin.service;

import com.csc3402.security.formlogin.model.HealthData;
import com.csc3402.security.formlogin.repository.HealthDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HealthDataServiceImpl implements HealthDataService{

    private final HealthDataRepository healthDataRepository;

    public HealthDataServiceImpl(HealthDataRepository healthDataRepository){
        this.healthDataRepository = healthDataRepository;
    }

    // Method to list all data members
    @Override
    public List<HealthData> listAllDatas() {
        return healthDataRepository.findAll();
    }

    // Method to add a new data
    @Override
    public HealthData addNewData(HealthData healthData) {
        return healthDataRepository.save(healthData);
    }

    // Method to update an existing data's details
    @Override
    public void updateData(HealthData healthData) {
        healthDataRepository.save(healthData);
    }

    // Method to delete a data
    @Override
    @Transactional
    public void deleteData(HealthData healthData) {
        healthDataRepository.delete(healthData);
    }

    @Override
    public HealthData QueryHealthDataById(Integer healthDataId) {
        return healthDataRepository.findHealthDataById(healthDataId);
    }

    // Method to find a staff member by their ID
    @Transactional
    public Optional<HealthData> findHealthDataById(int id) {
        return healthDataRepository.findById(id);
    }

    @Override
    public List<HealthData> findHealthDataByPatientId(int patientId) {
        return healthDataRepository.findByPatient_PatientId(patientId);
    }
}
