package com.ezt.Gerence_jobs.modules.candidate.useCases;
import java.util.UUID;

import com.ezt.Gerence_jobs.exceptions.JobNotFoundException;
import com.ezt.Gerence_jobs.exceptions.UserNotFoundException;
import com.ezt.Gerence_jobs.modules.candidate.ApplyJobRepository;
import com.ezt.Gerence_jobs.modules.candidate.CandidateRepository;
import com.ezt.Gerence_jobs.modules.candidate.entity.ApplyJobEntity;
import com.ezt.Gerence_jobs.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private JobRepository jobRepository;

    // ID do candidato
    // ID da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}