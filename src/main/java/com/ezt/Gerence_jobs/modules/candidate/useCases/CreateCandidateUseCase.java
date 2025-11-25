package com.ezt.Gerence_jobs.modules.candidate.useCases;

import com.ezt.Gerence_jobs.exceptions.UserFoundException;
import com.ezt.Gerence_jobs.modules.candidate.CandidateEntity;
import com.ezt.Gerence_jobs.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail()).ifPresent((user)->{
            throw new UserFoundException();
        });

        return candidateRepository.save(candidateEntity);
    }
}
