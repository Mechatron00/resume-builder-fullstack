package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.model.Skills;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import com.resumebuilder.resumebuilder.repository.SkillsRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SkillsService
{
    @Autowired
    private SkillsRepo skillsRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createSkills(String resumeid, Skills skills)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Skills newSkill = skillsRepo.save(skills);

            Resume fetchResume = resume.get();
            fetchResume.getSkillsList().add(newSkill);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newSkill, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updateSkills(String id,Skills skills) {
        Optional<Skills> existed = skillsRepo.getSkillsById(id);
        if(existed.isPresent())
        {
            Skills oldSkill = existed.get();
            oldSkill.setSkill(skills.getSkill());
            oldSkill.setComptetence(skills.getComptetence());
            Skills updated = skillsRepo.save(oldSkill);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Skill not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getSkillsById(String id)
    {
        Optional<Skills> res = skillsRepo.getSkillsById(id);
        if (res.isPresent())
        {
            Skills skills = res.get();
            return new ResponseEntity<>(skills, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Skill not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteSkills(String id, String resumeid)
    {
        Optional<Skills> res = skillsRepo.findById(id);
        if(res.isPresent())
        {
            Skills skills = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Skills> skillsList = getResume.getSkillsList();
                skillsList.remove(skills);
                resumeRepo.save(getResume);
                skillsRepo.delete(skills);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }
    }
}
