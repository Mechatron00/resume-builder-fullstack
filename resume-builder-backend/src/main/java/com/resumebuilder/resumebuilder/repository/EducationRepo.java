package com.resumebuilder.resumebuilder.repository;

import com.resumebuilder.resumebuilder.model.Awards;
import com.resumebuilder.resumebuilder.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepo extends JpaRepository<Education, String>
{
    Optional<Education> getEducationById(String id);
}
