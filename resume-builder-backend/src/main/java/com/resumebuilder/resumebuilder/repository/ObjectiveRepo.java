package com.resumebuilder.resumebuilder.repository;

import com.resumebuilder.resumebuilder.model.Awards;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjectiveRepo extends JpaRepository<Objective, String>
{
    Optional<Objective> getObjectiveById(String id);




}
