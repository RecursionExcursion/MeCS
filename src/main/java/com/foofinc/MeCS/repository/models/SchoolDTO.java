package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolDTO {

    public long id;
    public String school;
    public String mascot;
    public String abbreviation;
    public String conference;
    public String color;
    public String altColor;
    public String[] logos;
}
