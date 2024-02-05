package com.example.SpringBatchTutorial.repository;

import com.example.SpringBatchTutorial.entity.SourcePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourcePersonRepository extends JpaRepository<SourcePerson, Integer> {
}
