package com.resumebuilder.resumebuilder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Resume
{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne
    private PersonalDetails personalDetails;
    @OneToMany(orphanRemoval = true)
    private List<Objective> objectiveList;
    @OneToMany(orphanRemoval = true)

    private List<Education> educationList;
    @OneToMany(orphanRemoval = true)

    private List<Experience> experienceList;
    @OneToMany(orphanRemoval = true)

    private List<Projects> projectsList;
    @OneToMany(orphanRemoval = true)

    private List<Awards> awardsList ;
    @OneToMany(orphanRemoval = true)

    private List<Certification> certificationList ;
    @OneToMany(orphanRemoval = true)

    private List<Skills> skillsList;
    @OneToMany(orphanRemoval = true)

    private List<PersonalSkills> personalSkillsList ;
    @OneToMany(orphanRemoval = true)

    private List<ExtraCurricular> extraCurricularList;
    @OneToMany(orphanRemoval = true)

    private List<Language> languageList ;
    @OneToMany(orphanRemoval = true)

    private List<Hobbies> hobbiesList ;

    public String getId() {
        return id;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<Objective> getObjectiveList() {
        return objectiveList;
    }

    public void setObjectiveList(List<Objective> objectiveList) {
        this.objectiveList = objectiveList;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public List<Awards> getAwardsList() {
        return awardsList;
    }

    public void setAwardsList(List<Awards> awardsList) {
        this.awardsList = awardsList;
    }

    public List<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    public List<PersonalSkills> getPersonalSkillsList() {
        return personalSkillsList;
    }

    public void setPersonalSkillsList(List<PersonalSkills> personalSkillsList) {
        this.personalSkillsList = personalSkillsList;
    }

    public List<ExtraCurricular> getExtraCurricularList() {
        return extraCurricularList;
    }

    public void setExtraCurricularList(List<ExtraCurricular> extraCurricularList) {
        this.extraCurricularList = extraCurricularList;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public List<Hobbies> getHobbiesList() {
        return hobbiesList;
    }

    public void setHobbiesList(List<Hobbies> hobbiesList) {
        this.hobbiesList = hobbiesList;
    }
}
