package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Awards;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.AwardsRepo;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardsService {
    @Autowired
    private AwardsRepo awardsRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create an award
    public ResponseEntity<?> createAward(String resumeid, Awards award) {

        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Awards newAward = awardsRepo.save(award);

            Resume fetchResume = resume.get();
            fetchResume.getAwardsList().add(newAward);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newAward, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");


    }

    //update


    public ResponseEntity<?> updateAward(String id,Awards awards)
    {

        Optional<Awards> existed = awardsRepo.findById(id);

        if(existed.isPresent())
        {
            Awards award =existed.get();
            award.setTitle(awards.getTitle());
            award.setIssuer(awards.getIssuer());
            award.setIssuedDate(awards.getIssuedDate());
            award.setDescription(awards.getDescription());
            Awards newAwards = awardsRepo.save(award);
            return new ResponseEntity<>(newAwards, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Award not found with id:"+id);
        }
    }

    //get Award
    public ResponseEntity<?> getAwardById(String id)
    {
        Optional<Awards> res=awardsRepo.getAwardsById(id);
        if(res.isPresent())
        {
            Awards awards=res.get();
            return new ResponseEntity<>(awards, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Award not found with id:"+id);
        }
    }

    //delete award
    public ResponseEntity<?> deleteAward(String id,String resumeid )
    {
        Optional<Awards> res = awardsRepo.getAwardsById(id);
        if(res.isPresent())
        {
            Awards award = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Awards> awardsList = getResume.getAwardsList();
                awardsList.remove(award);
                resumeRepo.save(getResume);
                awardsRepo.delete(award);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }





}
