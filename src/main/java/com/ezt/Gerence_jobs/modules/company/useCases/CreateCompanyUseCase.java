package com.ezt.Gerence_jobs.modules.company.useCases;

import com.ezt.Gerence_jobs.exceptions.UserFoundException;
import com.ezt.Gerence_jobs.modules.company.entities.CompanyEntity;
import com.ezt.Gerence_jobs.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(),companyEntity.getEmail()).ifPresent((user)->{
            throw new UserFoundException();
        });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return companyRepository.save(companyEntity);
    }
}
