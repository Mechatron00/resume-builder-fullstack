package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Experience;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.ExperienceRepo;
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
public class ExperienceService
{
    @Autowired
    private ExperienceRepo experienceRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createExperience(String resumeid,Experience experience)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Experience newExperience = experienceRepo.save(experience);

            Resume fetchResume = resume.get();
            fetchResume.getExperienceList().add(newExperience);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newExperience, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updateExperience(String id,Experience experience)
    {
        Optional<Experience> existed = experienceRepo.getExperienceById(id);
        if(existed.isPresent())
        {
            Experience oldExperience = existed.get();
            oldExperience.setCompany(experience.getCompany());
            oldExperience.setLocation(experience.getLocation());
            oldExperience.setPosition(experience.getPosition());
            oldExperience.setStartDate(experience.getStartDate());
            oldExperience.setEndDate(experience.getEndDate());
            oldExperience.setDescription(experience.getDescription());
            Experience updated = experienceRepo.save(oldExperience);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Experience not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getExperienceById(String id)
    {
        Optional<Experience> res = experienceRepo.getExperienceById(id);
        if (res.isPresent())
        {
            Experience experience = res.get();
            return new ResponseEntity<>(experience, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Experience not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteExperience(String id, String resumeid)
    {
        Optional<Experience> res = experienceRepo.findById(id);
        if(res.isPresent())
        {
            Experience experience = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Experience> experienceList = getResume.getExperienceList();
                experienceList.remove(experience);
                resumeRepo.save(getResume);
                experienceRepo.delete(experience);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }
}
