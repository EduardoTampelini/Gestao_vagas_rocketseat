package com.ezt.Gerence_jobs.modules.candidate.controllers;

import com.ezt.Gerence_jobs.exceptions.UserFoundException;
import com.ezt.Gerence_jobs.modules.candidate.CandidateEntity;
import com.ezt.Gerence_jobs.modules.candidate.CandidateRepository;
import com.ezt.Gerence_jobs.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
       try{
           var result = createCandidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok().body(result);
       }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}
