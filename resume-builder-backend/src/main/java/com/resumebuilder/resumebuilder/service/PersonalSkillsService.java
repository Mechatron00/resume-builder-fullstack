package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.PersonalSkills;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.EducationRepo;
import com.resumebuilder.resumebuilder.repository.PersonalSkillsRepo;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalSkillsService
{
    @Autowired
    private PersonalSkillsRepo personalSkillsRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createPersonalSkills(String resumeid,PersonalSkills personalSkills)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            PersonalSkills newPersonalskill = personalSkillsRepo.save(personalSkills);

            Resume fetchResume = resume.get();
            fetchResume.getPersonalSkillsList().add(newPersonalskill);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newPersonalskill, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updatePersonalSkills(String id, PersonalSkills personalSkills ){
        Optional<PersonalSkills> existed = personalSkillsRepo.getPersonalSkillsById(id);
        if(existed.isPresent())
        {
            PersonalSkills oldPersonalSkills = existed.get();
            oldPersonalSkills.setDescription(personalSkills.getDescription());
            PersonalSkills updated = personalSkillsRepo.save(oldPersonalSkills);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else
        {
            return  ResponseEntity.badRequest().body("Personal skill not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getPersonalSkillsById(String id)
    {
        Optional<PersonalSkills> res = personalSkillsRepo.getPersonalSkillsById(id);
        if (res.isPresent())
        {
            PersonalSkills personalSkills = res.get();
            return new ResponseEntity<>(personalSkills, HttpStatus.OK);
        }
        else
        {
            return  ResponseEntity.badRequest().body("Personal skill not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deletePersonalSkills(String id, String resumeid)
    {
        Optional<PersonalSkills> res = personalSkillsRepo.findById(id);
        if(res.isPresent())
        {
            PersonalSkills personalSkills = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<PersonalSkills> personalSkillsList = getResume.getPersonalSkillsList();
                personalSkillsList.remove(personalSkills);
                resumeRepo.save(getResume);
                personalSkillsRepo.delete(personalSkills);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }
    }
}
