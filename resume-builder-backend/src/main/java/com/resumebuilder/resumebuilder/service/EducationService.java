package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.EducationRepo;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EducationService
{
    @Autowired
    private EducationRepo educationRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createEducation(String resumeid, Education education)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Education newEducation = educationRepo.save(education);

            Resume fetchResume = resume.get();
            fetchResume.getEducationList().add(newEducation);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newEducation, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");


    }

    //update
    public ResponseEntity<?> updateEducation(String id, Education education)
    {
        Optional<Education> existed = educationRepo.getEducationById(id);
        if(existed.isPresent())
        {
            Education OldEducation = existed.get();
            OldEducation.setInstitute_name(education.getInstitute_name());
            OldEducation.setDegree(education.getDegree());
            OldEducation.setBranch(education.getBranch());
            OldEducation.setStartDate(education.getStartDate());
            OldEducation.setEndDate(education.getEndDate());
            OldEducation.setScore(education.getScore());
            OldEducation.setDescription(education.getDescription());

            Education updated = educationRepo.save(OldEducation);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Education not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getEducationById(String id)
    {
        Optional<Education> res = educationRepo.getEducationById(id);
        if (res.isPresent())
        {
            Education education = res.get();
            return new ResponseEntity<>(education, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Education not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteEducation(String id, String resumeid)
    {
        Optional<Education> res = educationRepo.findById(id);
        if(res.isPresent())
        {
            Education education = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Education> educationList = getResume.getEducationList();
                educationList.remove(education);
                resumeRepo.save(getResume);

            }
            educationRepo.delete(education);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }
}
