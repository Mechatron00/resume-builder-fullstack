package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.Hobbies;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.EducationRepo;
import com.resumebuilder.resumebuilder.repository.HobbiesRepo;
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
public class HobbiesService
{
    @Autowired
    private HobbiesRepo hobbiesRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createHobby(String resumeid, Hobbies hobbies)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Hobbies newHobby = hobbiesRepo.save(hobbies);

            Resume fetchResume = resume.get();
            fetchResume.getHobbiesList().add(newHobby);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newHobby, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updateHobbies(String id, Hobbies hobbies )  {
        Optional<Hobbies> existed = hobbiesRepo.getHobbiesById(id);
        if(existed.isPresent())
        {
            Hobbies oldHobby = existed.get();
            oldHobby.setHobby(hobbies.getHobby());
            Hobbies updated = hobbiesRepo.save(oldHobby);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Hobby not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getHobbyById(String id)
    {
        Optional<Hobbies> res = hobbiesRepo.getHobbiesById(id);
        if (res.isPresent())
        {
            Hobbies hobbies = res.get();
            return new ResponseEntity<>(hobbies, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Hobby not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteHobby(String id, String resumeid)
    {

        Optional<Hobbies> res = hobbiesRepo.findById(id);
        if(res.isPresent())
        {
            Hobbies hobby = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Hobbies> hobbiesList = getResume.getHobbiesList();
                hobbiesList.remove(hobby);
                resumeRepo.save(getResume);
                hobbiesRepo.delete(hobby);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }

    }
}
