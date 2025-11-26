package com.ezt.Gerence_jobs.modules.company.useCases;

import com.ezt.Gerence_jobs.modules.company.dto.AuthCompanyDTO;
import com.ezt.Gerence_jobs.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AurthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(()->{
            throw new UsernameNotFoundException("Compania não localiozado");
        });
        var passwordMatchs= passwordEncoder.matches(authCompanyDTO.getPassword(),company.getPassword());
        if(!passwordMatchs){
            throw new AuthenticationException();
        }
    }
}
