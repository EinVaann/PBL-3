package com.project.pbl3.repositories;

import com.project.pbl3.model.subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<subjects,Integer> {
}