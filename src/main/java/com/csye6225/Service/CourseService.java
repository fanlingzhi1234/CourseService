package com.csye6225.Service;

import com.csye6225.datamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class CourseService {
    static HashMap<Long, Course> course_Map = InMemoryDatabase.getCourseDB();

    public CourseService(){

    }

    // Getting a list of all course
    public List<Course> getAllCourses() {
        //Getting the list
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.addAll(course_Map.values());
//        for (Course course : course_Map.values()) {
//            courseList.add(course);
//        }
        return courseList ;
    }

    // Getting One Course
    public Course getCourse(Long courseId) {

        //Simple HashKey Load
        Course course = course_Map.get(courseId);
        System.out.println("Item retrieved:");
        System.out.println(course.toString());

        return course;
    }
    // Getting a list of target professor courses
    public List<Course> getAllCoursesByProfessor(String professor) {
        //Getting the list
        ArrayList<Course> courseList = new ArrayList<>();
        for (Course course : course_Map.values()) {
            if(course.getProfessor().getProfessorId().equals(professor)){
                courseList.add(course);
            }
        }
        return courseList ;
    }

    // Adding a Course
    public Course addCourse(String name, Roster roster, ArrayList<Lecture> lectures, Professor professor) {
        // Next Id
        long nextAvailableId = course_Map.size() + 1;

        //Create a Professor Object
        Course course = new Course(nextAvailableId, name , roster,
                lectures, professor);

        course_Map.put(nextAvailableId, course);
        return course;
    }

    // Deleting a course
    public Course deleteCourse(Long courseId) {
        Course deletedCourseDetails = course_Map.get(courseId);
        course_Map.remove(courseId);
        return deletedCourseDetails;
    }

    // Updating the course info
    public Course updateCourse(Long courseId, Course updateCourse){
        Course course = course_Map.get(courseId);
        course.setName(updateCourse.getName());
        course.setBoard(updateCourse.getBoard());
        course.setEnrolledStudent(updateCourse.getEnrolledStudent());
        course.setLectures(updateCourse.getLectures());
        course.setProfessor(updateCourse.getProfessor());
        course.setRoster(updateCourse.getRoster());
        course.setTA(updateCourse.getTA());
        course_Map.put(courseId, course);
        return course;
    }

    // Publish a message in board;

    // add lecture to list;

    // change professor

    // change TA

    // change Roaster

    // add enrolled student
    public Course addStudent(Long courseId, Student student){
        Course course = course_Map.get(courseId);
        ArrayList<Student> studentlist = course.getEnrolledStudent();
//        for(int i =0; i< studentlist.size(); i++){
//            if(studentlist.get(i).getStudentId().equals(student.getStudentId())){
//                throw new Exception("already enrolled");
//            }
//        }
        studentlist.add(student);
        course.setEnrolledStudent(studentlist);
        course_Map.put(courseId, course);
        return course;
    }

    // remove student
    public Course withdrawStudent(Long courseId, Student student){

        Course course = course_Map.get(courseId);
        ArrayList<Student> studentlist = course.getEnrolledStudent();
//        for(int i =0; i< studentlist.size(); i++){
//            if(studentlist.get(i).getStudentId().equals(student.getStudentId())){
                studentlist.remove(student);
//            }
//            else{
//                throw new Exception("you dont enroll this course;");
//            }
//        }
        course.setEnrolledStudent(studentlist);
        course_Map.put(courseId, course);
        return course;
    }




}
