package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Awards;
import com.resumebuilder.resumebuilder.service.AwardsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/awards")
public class AwardsController
{
    @Autowired
    private AwardsService awardsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getAwards(@PathVariable("id") String id)
    {
        return awardsService.getAwardById(id);
    }

    /*private String title;
    private String issuer;
    private Date issuedDate;
    private String description;
    */

    @PostMapping("/{id}")
    public ResponseEntity<?> addAwards(@PathVariable("id") String id, @RequestBody Awards awards)
    {
            return awardsService.createAward(id, awards);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAwards(@PathVariable("id") String id,@RequestBody Awards awards)
    {
            return awardsService.updateAward(id,awards );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAward(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
       return awardsService.deleteAward(id,resumeid);
    }
}
