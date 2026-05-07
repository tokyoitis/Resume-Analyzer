package com.resumeanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeanalyzer.entity.Resumes;

@Repository
public interface ResumeRepository extends JpaRepository<Resumes,Long> {

    List<Resumes> findByUserId(Long UserId);

    List<Resumes> findByUserIdOrderByUploadedAtDesc(Long UserId);
    
}
