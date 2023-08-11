package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.ExtraCurricular;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.EducationRepo;
import com.resumebuilder.resumebuilder.repository.ExtraCurricularRepo;
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
public class ExtraCurricularService
{
    @Autowired
    private ExtraCurricularRepo extraCurricularRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createExtraCurricular(String resumeid, ExtraCurricular extraCurricular)
    {

        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            ExtraCurricular newExtracurricular = extraCurricularRepo.save(extraCurricular);

            Resume fetchResume = resume.get();
            fetchResume.getExtraCurricularList().add(newExtracurricular);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newExtracurricular, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");

    }

    //update
    public ResponseEntity<?> updateExtracurricular(String id, ExtraCurricular extraCurricular )
    {
        Optional<ExtraCurricular> existed = extraCurricularRepo.getExtraCurricularById(id);
        if(existed.isPresent())
        {
            ExtraCurricular oldExtraCurricular = existed.get();

            oldExtraCurricular.setDescription(extraCurricular.getDescription());
            ExtraCurricular updated = extraCurricularRepo.save(oldExtraCurricular);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
             return ResponseEntity.badRequest().body("Extracurricular not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getExtraCurricularById(String id)
    {
        Optional<ExtraCurricular> res = extraCurricularRepo.getExtraCurricularById(id);
        if (res.isPresent())
        {
            ExtraCurricular extraCurricular = res.get();
            return new ResponseEntity<>(extraCurricular, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Extracurricular not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteExtraCurricular(String id, String resumeid)
    {
        Optional<ExtraCurricular> res = extraCurricularRepo.findById(id);
        if(res.isPresent())
        {
            ExtraCurricular extraCurricular = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<ExtraCurricular> extraCurricularList = getResume.getExtraCurricularList();
                extraCurricularList.remove(extraCurricular);
                resumeRepo.save(getResume);
                extraCurricularRepo.delete(extraCurricular);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Objective not found with id:"+id);
        }
    }
}
