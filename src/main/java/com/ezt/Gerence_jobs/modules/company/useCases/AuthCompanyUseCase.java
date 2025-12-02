package com.ezt.Gerence_jobs.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ezt.Gerence_jobs.modules.company.dto.AuthCompanyDTO;
import com.ezt.Gerence_jobs.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(()->{
            throw new UsernameNotFoundException("Username/Password está incorreta");
        });
        var passwordMatchs= passwordEncoder.matches(authCompanyDTO.getPassword(),company.getPassword());
        if(!passwordMatchs){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
       var token = JWT.create().withIssuer("javagas").withExpiresAt(Instant.now().plus(Duration.ofHours(2))).withSubject(company.getId().toString()).sign(algorithm);
        return token;
    }
}
