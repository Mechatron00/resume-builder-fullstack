package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.*;
import com.resumebuilder.resumebuilder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeService
{
    @Autowired
    private ResumeRepo resumeRepo;


    @Autowired
    private AwardsRepo awardsRepo;
    @Autowired
    private CertificationRepo certificationRepo;
    @Autowired
    private EducationRepo educationRepo;
    @Autowired
    private ExtraCurricularRepo extraCurricularRepo;
    @Autowired
    private HobbiesRepo hobbiesRepo;
    @Autowired
    private ExperienceRepo experienceRepo;
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private ObjectiveRepo objectiveRepo;
    @Autowired
    private PersonalSkillsRepo personalSkillsRepo;
    @Autowired
    private ProjectsRepo projectsRepo;
    @Autowired
    private SkillsRepo skillsRepo;
    @Autowired
    private PersonalDetailsRepo personalDetailsRepo;



    //create
    public ResponseEntity<?> createResume()
    {
        Resume resume = new Resume();
        resumeRepo.save(resume);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }


    public ResponseEntity<?> updateResume(String id)
    {
        Optional<Resume> resume = resumeRepo.findById(id);
        if (resume.isPresent())
        {
            Resume getResume = resume.get();
            return new ResponseEntity<>(getResume,HttpStatus.OK);
        }
        else return ResponseEntity.badRequest().body("not found");
    }

    //get resume
    public Optional<Resume> getResumeById(String id)
    {
        Optional<Resume> res=resumeRepo.getResumeById(id);
        if(res.isPresent())
        {
            Resume resume=res.get();
            return Optional.of(resume);
        }
        else
        {
            return Optional.empty();
        }
    }


    //delete
    public ResponseEntity deleteResume(String id)
    {
        Optional<Resume> res = resumeRepo.getResumeById(id);
        if (res.isPresent())
        {
            Resume resume = res.get();
            resumeRepo.delete(resume);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Resume not found with id:"+id);
        }
    }




//methods for getting lists of attributes
    private List<Awards> getAwards()
    {
        List<Awards> awardsList = new ArrayList<>();
        awardsList = awardsRepo.findAll();
        return awardsList;
    }
    private List<Certification> getCertifications()
    {
        List<Certification> certificationList = new ArrayList<>();
        certificationList = certificationRepo.findAll();
        return certificationList;
    }
    private List<Education> getEducations()
    {
        List<Education> educationList = new ArrayList<>();
        educationList = educationRepo.findAll();
        return educationList;
    }
    private List<ExtraCurricular> getExtracurriculars()
    {
        List<ExtraCurricular> extraCurricularList = new ArrayList<>();
        extraCurricularList = extraCurricularRepo.findAll();
        return extraCurricularList;
    }
    private List<Hobbies> getHobbies()
    {
        List<Hobbies> hobbiesList = new ArrayList<>();
        hobbiesList = hobbiesRepo.findAll();
        return hobbiesList;
    }
    private List<Experience> getExperiences()
    {
        List<Experience> experienceList = new ArrayList<>();
        experienceList = experienceRepo.findAll();
        return experienceList;
    }
    private List<Language> getLanguages()
    {
        List<Language> languageList = new ArrayList<>();
        languageList = languageRepo.findAll();
        return languageList;
    }
//    private List<Objective> getObjectives()
//    {
//        List<Objective> objectiveList = new ArrayList<>();
//        objectiveList = objectiveRepo.findAll();
//        return objectiveList;
//    }
private Optional<Objective> getObjectives(String resumeId) {
    // Retrieve the objectives associated with the given resumeId from the database
    Optional<Objective> objectiveList = objectiveRepo.findById(resumeId);
    return objectiveList;
}
    private List<PersonalSkills> getPersonalskills()
    {
        List<PersonalSkills> personalSkillsList = new ArrayList<>();
        personalSkillsList = personalSkillsRepo.findAll();
        return personalSkillsList;
    }
    private List<Projects> getProjects()
    {
        List<Projects> projectsList = new ArrayList<>();
        projectsList = projectsRepo.findAll();
        return projectsList;
    }
    private List<Skills> getSkills()
    {
        List<Skills> skillsList = new ArrayList<>();
        skillsList = skillsRepo.findAll();
        return skillsList;
    }

    private PersonalDetails getDetails(String id)
    {
      Optional<PersonalDetails> details = personalDetailsRepo.findById(id);
      PersonalDetails getDetails = details.get();
        return getDetails;
    }

}
