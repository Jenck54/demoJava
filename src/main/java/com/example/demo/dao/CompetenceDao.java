package com.example.demo.dao;

import com.example.demo.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceDao extends JpaRepository<Competence, Integer> {
}
