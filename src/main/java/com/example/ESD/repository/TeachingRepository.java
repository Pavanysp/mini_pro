package com.example.ESD.repository;

import com.example.ESD.model.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeachingRepository extends JpaRepository<Teaching, Integer> {
    List<Teaching> findByFaculty_Username(String username);  // Find courses taught by faculty
}
