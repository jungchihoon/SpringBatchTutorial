package com.example.SpringBatchTutorial.repository;

import com.example.SpringBatchTutorial.entity.TargetPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetPersonRepository extends JpaRepository<TargetPerson, Integer> {
}