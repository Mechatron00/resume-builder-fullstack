package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.ExtraCurricular;
import com.resumebuilder.resumebuilder.service.ExtraCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/extracurricular")
public class ExtracurricularController
{
    @Autowired
    private ExtraCurricularService extraCurricularService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getExtracurricular(@PathVariable("id") String id)
    {
        return extraCurricularService.getExtraCurricularById(id);
    }

    /*
     private String description;
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addExtracurricular(@PathVariable("id") String id, @RequestBody ExtraCurricular extraCurricular)
    {
            return extraCurricularService.createExtraCurricular(id, extraCurricular);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExtracurricular(@PathVariable("id") String id,
                                                  @RequestBody ExtraCurricular extraCurricular
    )
    {
            return extraCurricularService.updateExtracurricular(id,extraCurricular);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExtracurricular(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
      return  extraCurricularService.deleteExtraCurricular(id,resumeid);

    }
}
