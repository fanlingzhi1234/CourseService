package com.csye6225.datamodel;


import java.util.ArrayList;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class Course {


    private Long courseId;
    private String name;
    private Roster roster;
    private ArrayList<String> board;
    private ArrayList<Lecture> lectures;
    private ArrayList<Student> enrolledStudent;
    private Professor professor;
    private Student TA;

    public Course(){

    }

    public Course(Long courseId, String name, Roster roster, ArrayList<Lecture> lectures, Professor professor) {
        this.courseId = courseId;
        this.name = name;
        this.roster = roster;
        this.lectures = lectures;
        this.professor = professor;
    }

    public Course(Long courseId, String name, ArrayList<String> board, Roster roster, ArrayList<Lecture> lectures, ArrayList<Student> enrolledStudent, Professor professor, Student TA) {
        this.courseId = courseId;
        this.name = name;
        this.board = board;
        this.roster = roster;
        this.lectures = lectures;
        this.enrolledStudent = enrolledStudent;
        this.professor = professor;
        this.TA = TA;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<String> board) {
        this.board = board;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public ArrayList<Student> getEnrolledStudent() {
        return enrolledStudent;
    }

    public void setEnrolledStudent(ArrayList<Student> enrolledStudent) {
        this.enrolledStudent = enrolledStudent;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Student getTA() {
        return TA;
    }

    public void setTA(Student TA) {
        this.TA = TA;
    }
}
