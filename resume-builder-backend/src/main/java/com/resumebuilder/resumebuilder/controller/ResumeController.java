package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import com.resumebuilder.resumebuilder.service.EducationService;
import com.resumebuilder.resumebuilder.service.ResumeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/resume")
public class ResumeController
{
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ResumeRepo resumeRepo;



    @GetMapping("/{id}")
    public ResponseEntity<?> getResume(@PathVariable("id") String id)
    {
        return resumeService.updateResume(id);
    }

    @PostMapping
    public ResponseEntity<?> createResume()
    {
            return resumeService.createResume();
    }

    @GetMapping("/getresume/{id}")
    public Optional<Resume> getResumeById(@PathVariable("id") String id)
    {
        return resumeService.getResumeById(id);
    }
    //there is no need of update and delete resume
    // you can implement this apis in Angular or React to use external templates
}
