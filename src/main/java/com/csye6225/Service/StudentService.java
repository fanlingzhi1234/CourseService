package com.csye6225.Service;

import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.InMemoryDatabase;
import com.csye6225.datamodel.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class StudentService {
    static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();

    public StudentService() {
    }

    // Getting a list of all student
    public List<Student> getAllStudents() {
        //Getting the list
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.addAll(student_Map.values());
//        for (Student student : student_Map.values()) {
//            studentList.add(student);
//        }
        return studentList ;
    }

    // Getting One student
    public Student getStudent(Long studentId) {

        //Simple HashKey Load
        Student stu = student_Map.get(studentId);
        System.out.println("Item retrieved:");
        System.out.println(stu.toString());

        return stu;
    }

    // Adding a student
    public Student addStudent(String firstName, String lastName, String image, String program) {
        // Next Id
        long nextAvailableId = student_Map.size() + 1;

        //Create a Professor Object
        Student stu = new Student(nextAvailableId, firstName , lastName,
                image, program);

        student_Map.put(nextAvailableId, stu);
        return stu;
    }

    // Deleting a student
    public Student deleteStudent(Long stuId) {
        Student deletedStudentDetails = student_Map.get(stuId);
        student_Map.remove(stuId);
        return deletedStudentDetails;
    }

    //updating student info
    public Student updateStudentInfo(Long stuId, Student updatedStudent){
        Student student = student_Map.get(stuId);
        student.setImage(updatedStudent.getImage());
        student.setCoursesEnrolled(updatedStudent.getCoursesEnrolled());
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setProgram(updatedStudent.getProgram());
        student_Map.put(stuId,student);
        return student;
    }

    // get all course enrolled
    public ArrayList<Course> getEnrolledCourse(Long stuId) {
        Student student = student_Map.get(stuId);
        return student.getCoursesEnrolled();
    }

    // enroll a course
    public Student enrollCourse(Long stuId, Course course) throws Exception{
        Student student = student_Map.get(stuId);
        ArrayList<Course> courselist = student.getCoursesEnrolled();
        for(int i =0; i< courselist.size(); i++){
            if(courselist.get(i).getCourseId().equals(course.getCourseId())){
                throw new Exception("already enrolled");
            }
        }
        courselist.add(course);
        student.setCoursesEnrolled(courselist);
        student_Map.put(stuId, student);
        return student;

    }

    // withdraw a course
    public Student withdrawCourse(Long stuId, Course course) throws Exception{
        Student student = student_Map.get(stuId);
        ArrayList<Course> courselist = student.getCoursesEnrolled();
        for(int i =0; i< courselist.size(); i++){
            if(courselist.get(i).getCourseId().equals(course.getCourseId())){
                courselist.remove(course);
            }
            else{
                throw new Exception("you dont enroll this course;");
            }
        }
        student.setCoursesEnrolled(courselist);
        student_Map.put(stuId, student);
        return student;
    }


    // change pic
    public Student updateImage(Long stuId, String newImage){
        Student student = student_Map.get(stuId);
        student.setImage(newImage);
        student_Map.put(stuId, student);
        return student;
    }

    // Get Students in a program
    public List<Student> getStudentByProgram(String program) {
        //Getting the list
        ArrayList<Student> list = new ArrayList<>();
        for (Student stu : student_Map.values()) {
            if (stu.getProgram().equals(program)) {
                list.add(stu);
            }
        }
        return list ;
    }
}
