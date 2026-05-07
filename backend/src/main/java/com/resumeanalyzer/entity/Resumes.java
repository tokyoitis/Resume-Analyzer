package com.resumeanalyzer.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.websocket.Decoder.Text;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resumes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name",nullable = false)
    private String file_name;

    @Column(name = "extracted_text",columnDefinition = "Text")
    private String extractedText;

    @Column(name = "summary" ,columnDefinition = "Text")
    private String summary;

    @Column(name = "experience_years")
    private String experienceYears;

    @Column(name = "overall_score")
    private Double overallScore;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Skills> skills;

    @PrePersist
    protected void onCreate(){
        this.uploadedAt=LocalDateTime.now();
    }


}
