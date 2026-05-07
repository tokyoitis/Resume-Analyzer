package com.resumeanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeanalyzer.entity.Skills;

@Repository
public interface SkillRepository extends JpaRepository<Skills,Long>{

    List<Skills> findByResumeId(Long resumeId);

    List<Skills> findByResumeIdAndCategory(Long resumeId, String category);
    
}
