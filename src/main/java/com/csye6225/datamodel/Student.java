package com.csye6225.datamodel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String image;
    private ArrayList<Course> coursesEnrolled;
    private String program;
    public Student(){

    }

    public Student(Long studentId, String firstName, String lastName, String image, String program) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.program = program;
    }

    public Student(Long studentId, String firstName, String lastName, String image, ArrayList<Course> coursesEnrolled, String program) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.coursesEnrolled = coursesEnrolled;
        this.program = program;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(ArrayList<Course> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", coursesEnrolled=" + coursesEnrolled +
                ", program='" + program + '\'' +
                '}';
    }
}
