package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Certification;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.CertificationRepo;
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
public class CertificationService
{
    @Autowired
    private CertificationRepo certificationRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createCertification(String resumeid, Certification certification)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Certification newCertification = certificationRepo.save(certification);

            Resume fetchResume = resume.get();
            fetchResume.getCertificationList().add(newCertification);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newCertification, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");

    }

    //update
    public ResponseEntity<?> updateCertification(String id,Certification certification )
    {
        Optional<Certification> existed = certificationRepo.findById(id);
        if(existed.isPresent())
        {
            Certification oldCertification = existed.get();
            oldCertification.setTitle(certification.getTitle());
            oldCertification.setWeblink(certification.getWeblink());
            oldCertification.setIssuedDate(certification.getIssuedDate());
            oldCertification.setDescription(certification.getDescription());

            Certification updated = certificationRepo.save(oldCertification);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Certification not found with id:"+id);
        }
    }

    //get certification
    public ResponseEntity<?> getCertificationById(String id)
    {
        Optional<Certification> res = certificationRepo.getCertificationById(id);
        if(res.isPresent())
        {
            Certification certification = res.get();
            return new ResponseEntity<>(certification, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Certification not found with id:"+id);
        }
    }

    //delete
    public ResponseEntity<?> deleteCertification(String id, String resumeid)
    {
        Optional<Certification> res = certificationRepo.findById(id);
        if(res.isPresent())
        {
            Certification certification = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Certification> resumeList = getResume.getCertificationList();
                resumeList.remove(certification);
                resumeRepo.save(getResume);
                certificationRepo.delete(certification);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }
}
