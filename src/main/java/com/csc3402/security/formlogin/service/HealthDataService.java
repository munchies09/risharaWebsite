package com.csc3402.security.formlogin.service;

import com.csc3402.security.formlogin.model.HealthData;

import java.util.List;
import java.util.Optional;

public interface HealthDataService {

        // Method to list all staff members
        List<HealthData> listAllDatas();

        // Method to add a new staff member
        HealthData addNewData(HealthData healthData);

        HealthData QueryHealthDataById(Integer healthDataId);

        // Method to find a staff member by their ID
        Optional<HealthData> findHealthDataById(int id);

        // Method to update an existing staff member's details
        void updateData(HealthData healthData);

        // Method to delete a staff member
        void deleteData(HealthData healthData);

        List <HealthData> findHealthDataByPatientId (int patientId);

}
