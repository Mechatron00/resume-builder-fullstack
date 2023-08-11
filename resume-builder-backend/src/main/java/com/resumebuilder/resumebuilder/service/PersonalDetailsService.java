package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.PersonalDetails;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.PersonalDetailsRepo;
import com.resumebuilder.resumebuilder.repository.PersonalSkillsRepo;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalDetailsService {
    @Autowired
    private PersonalDetailsRepo personalDetailsRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createPersonalDetails(String id, PersonalDetails personalDetails) {
        Optional<Resume> resume = resumeRepo.findById(id);
        if(resume.isPresent())
        {
            PersonalDetails details = personalDetailsRepo.save(personalDetails);

            Resume fetchResume = resume.get();
            fetchResume.setPersonalDetails(details);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(details, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //get
    public ResponseEntity<?> getPersonalDetails(String id) {
        Optional<PersonalDetails> getDetails = personalDetailsRepo.findById(id);
        if (getDetails.isPresent()) {
            PersonalDetails details = getDetails.get();
            return new ResponseEntity<>(details, HttpStatus.OK);
        } else return ResponseEntity.badRequest().body("Data not found with id:" + id);
    }

    //update
    public ResponseEntity<?> updatePersonalDetails(String id, PersonalDetails personalDetails) {
        Optional<PersonalDetails> getDetails = personalDetailsRepo.findById(id);
        if (getDetails.isPresent()) {
            PersonalDetails details = getDetails.get();
            details.setFname(personalDetails.getFname());
            details.setLname(personalDetails.getLname());
            details.setEmail(personalDetails.getEmail());
            details.setGender(personalDetails.getGender());
            details.setDob(personalDetails.getDob());
            details.setContact(personalDetails.getContact());
            details.setCity(personalDetails.getCity());
            details.setState(personalDetails.getState());
            details.setLinkedin_link(personalDetails.getLinkedin_link());
            details.setGithublink(personalDetails.getGithublink());
            details.setWebsite(personalDetails.getWebsite());

            PersonalDetails updated = personalDetailsRepo.save(details);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else return ResponseEntity.badRequest().body("Data not found with id:" + id);
    }

    //delete



}