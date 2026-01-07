package com.ezt.Gerence_jobs.modules.candidate;

import com.ezt.Gerence_jobs.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository  extends JpaRepository<ApplyJobEntity, UUID> {
}
