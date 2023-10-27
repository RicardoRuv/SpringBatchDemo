package com.deloitte.sprintbatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.sprintbatch.model.Incident;
import com.deloitte.sprintbatch.repository.IncidentRepository;

@RestController
public class DataController {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

}
