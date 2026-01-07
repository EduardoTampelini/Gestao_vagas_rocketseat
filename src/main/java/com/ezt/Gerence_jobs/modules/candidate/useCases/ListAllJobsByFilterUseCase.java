package com.ezt.Gerence_jobs.modules.candidate.useCases;

import com.ezt.Gerence_jobs.modules.company.entities.JobEntity;
import com.ezt.Gerence_jobs.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {
    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
        return jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
