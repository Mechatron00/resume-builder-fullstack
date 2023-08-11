package com.resumebuilder.resumebuilder.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Language
{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String language;
    private String profiency;


    public String getId() {
        return id;
    }



    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProfiency() {
        return profiency;
    }

    public void setProfiency(String profiency) {
        this.profiency = profiency;
    }


}
