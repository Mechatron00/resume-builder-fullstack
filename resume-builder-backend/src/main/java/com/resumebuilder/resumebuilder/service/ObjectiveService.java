package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.ObjectiveRepo;
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
public class ObjectiveService
{
    @Autowired
    private ObjectiveRepo objectiveRepo;
    @Autowired
    private ResumeRepo resumeRepo;
    @Autowired
    private ResumeService resumeService;

    //create
    public ResponseEntity<?> createObjective(String id, Objective objective)
    {
//        System.out.println(id);
        Optional<Resume> resume = resumeRepo.findById(id);
//        System.out.println(resume);
        if(resume.isPresent() )
        {
            Objective newObjective = objectiveRepo.save(objective);

            Resume fetchResume = resume.get();
            fetchResume.getObjectiveList().add(newObjective);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newObjective, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updateObjective(String id,Objective objective ) {
        Optional<Objective> existed = objectiveRepo.getObjectiveById(id);
        if(existed.isPresent())
        {
            Objective oldObjective = existed.get();
            oldObjective.setObjective(objective.getObjective());
            Objective updated = objectiveRepo.save(oldObjective);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getObjectiveById(String id)
    {
        Optional<Objective> res = objectiveRepo.getObjectiveById(id);
        if (res.isPresent())
        {
            Objective objective = res.get();
            return new ResponseEntity<>(objective, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity deleteObjective(String id,String resumeid)
    {

        Optional<Objective> res = objectiveRepo.getObjectiveById(id);
        if(res.isPresent())
        {
            Objective objective = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Objective> resumeList = getResume.getObjectiveList();
                resumeList.remove(objective);
                resumeRepo.save(getResume);
                objectiveRepo.delete(objective);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }
    }
}
