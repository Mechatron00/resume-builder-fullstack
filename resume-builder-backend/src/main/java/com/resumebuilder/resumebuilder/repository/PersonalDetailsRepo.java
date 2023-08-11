package com.resumebuilder.resumebuilder.repository;

import com.resumebuilder.resumebuilder.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDetailsRepo  extends JpaRepository<PersonalDetails,String>
{

}
