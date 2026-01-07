package com.ezt.Gerence_jobs.modules.candidate.useCases;

import com.ezt.Gerence_jobs.modules.candidate.CandidateRepository;
import com.ezt.Gerence_jobs.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute (UUID idCandidate){
        var candidate = candidateRepository.findById(idCandidate)
                .orElseThrow(()->{
                    throw new UsernameNotFoundException("user not found");
                });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDesccription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .build();
        return candidateDTO;
    }
}
