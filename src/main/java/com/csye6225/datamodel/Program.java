package com.csye6225.datamodel;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Rexus
 * @date 2019-10-23
 */
public class Program {
    private Long programId;
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<Professor> professors;

    public Program(){

    }
    public Program(Long programId, String name){
        this.programId = programId;
        this.name = name;
    }

    public Program(Long programId, String name, ArrayList<Course> courses, ArrayList<Professor> professors) {
        this.programId = programId;
        this.name = name;
        this.courses = courses;
        this.professors = professors;
        
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }
}
