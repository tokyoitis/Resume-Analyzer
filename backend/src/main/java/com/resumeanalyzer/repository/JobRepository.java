package com.resumeanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeanalyzer.entity.Jobs;

@Repository
public interface JobRepository extends JpaRepository<Jobs,Long>{

    List<Jobs> findByUserId(Long userId);
    List<Jobs> findByUserIdOrderByCreatedAtDesc(Long userId);
    
}
