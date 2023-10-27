package com.deloitte.sprintbatch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.sprintbatch.model.Incident;

@Repository
public interface IncidentRepository extends MongoRepository<Incident, String> {

}
