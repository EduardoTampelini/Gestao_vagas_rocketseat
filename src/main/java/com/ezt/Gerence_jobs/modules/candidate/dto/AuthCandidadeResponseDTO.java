package com.ezt.Gerence_jobs.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidadeResponseDTO {
    private String access_token;
    private Long expire_in;

}
