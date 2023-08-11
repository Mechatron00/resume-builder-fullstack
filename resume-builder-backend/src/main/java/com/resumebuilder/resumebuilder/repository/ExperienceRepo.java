package com.resumebuilder.resumebuilder.repository;

import com.resumebuilder.resumebuilder.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepo extends JpaRepository<Experience, String>
{
    Optional<Experience> getExperienceById(String id);
}
