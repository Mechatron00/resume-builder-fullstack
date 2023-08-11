package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.PersonalDetails;
import com.resumebuilder.resumebuilder.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/personaldetails")
public class PersonalDetailsController
{
    @Autowired
    private PersonalDetailsService personalDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String id)
    {
        return personalDetailsService.getPersonalDetails(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createPersonalDetails(@PathVariable String id,@RequestBody PersonalDetails personalDetails)
    {
        return personalDetailsService.createPersonalDetails(id,personalDetails);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetails(@PathVariable String id,@RequestBody PersonalDetails personalDetails)
    {
        return personalDetailsService.updatePersonalDetails(id,personalDetails);
    }

    //delete mapping
}
