package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Certification;
import com.resumebuilder.resumebuilder.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/certification")
public class CertificationController
{
    @Autowired
    private CertificationService certificationService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getAwards(@PathVariable("id") String id)
    {
        return certificationService.getCertificationById(id);
    }

    /*
    private String title;
    private String weblink;
    private Date issuedDate;
    private String description;    */

    @PostMapping("/{id}")
    public ResponseEntity<?> addCertification(@PathVariable("id") String id, @RequestBody Certification certification)
    {
            return certificationService.createCertification(id,certification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCertification(@PathVariable("id") String id,
                                          @RequestBody Certification certification)
    {
            return certificationService.updateCertification(id,certification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertification(@PathVariable String id,  @RequestParam("resumeid") String resumeid)
    {
        return certificationService.deleteCertification(id, resumeid);
    }
}
