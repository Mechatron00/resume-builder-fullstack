package com.resumebuilder.resumebuilder.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Skills
{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String skill;
    private String comptetence;//levels-beginner,intermediate or expert

    public String getId() {
        return id;
    }



    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getComptetence() {
        return comptetence;
    }

    public void setComptetence(String comptetence) {
        this.comptetence = comptetence;
    }


}
