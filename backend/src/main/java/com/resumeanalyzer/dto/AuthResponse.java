package com.resumeanalyzer.dto;

import com.resumeanalyzer.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private Long id;

    private String name;

    private String email;

    private String role;

    private String token;  //jwt token sent back to fronten
    // d
    
}
