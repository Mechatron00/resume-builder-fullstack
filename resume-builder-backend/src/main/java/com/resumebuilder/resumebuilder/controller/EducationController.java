package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/education")
public class EducationController
{
    @Autowired
    private EducationService educationService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getEducation(@PathVariable("id") String id)
    {
        return educationService.getEducationById(id);
    }

    /*
     private String institute_name;
    private String degree;
    private String branch;
    private Date startDate;
    private Date endDate;

    private String score;
    private String description;
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addEducation(@PathVariable("id") String id, @RequestBody Education education)
    {
            return educationService.createEducation(id,education);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEducation(@PathVariable("id") String id,@RequestBody Education education)
    {
            return educationService.updateEducation(id,education);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
      return  educationService.deleteEducation(id, resumeid);

    }
}
