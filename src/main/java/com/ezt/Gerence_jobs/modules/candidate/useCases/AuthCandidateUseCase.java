package com.ezt.Gerence_jobs.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ezt.Gerence_jobs.modules.candidate.CandidateRepository;
import com.ezt.Gerence_jobs.modules.candidate.dto.AuthCandidadeResponseDTO;
import com.ezt.Gerence_jobs.modules.candidate.dto.AuthCandidateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;


    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidadeResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(()->{
            throw new UsernameNotFoundException("Username incorrect");
        });
        var passowrdMatcvhes = passwordEncoder.matches(authCandidateRequestDTO.password(),candidate.getPassword());

        if(!passowrdMatcvhes){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(30));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

      var authCandidateResponse = AuthCandidadeResponseDTO.builder().access_token(token).expire_in(expiresIn.toEpochMilli()).build();

      return authCandidateResponse;
    }
}
