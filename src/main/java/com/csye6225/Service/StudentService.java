package com.csye6225.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.datamodel.DynamoDbConnector;
import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author Rexus
 * @date 2019-10-18
 */
public class StudentService {


    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;
    public StudentService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }

    // Getting a list of all student
    public List<Student> getAllStudents() {
        return mapper.scan(Student.class, new DynamoDBScanExpression());
    }

    // Get Students in a department
    public List<Student> getStudentByDepartment(String department) {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":attribute1", new AttributeValue().withS(department));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("department = :attribute1")
                .withExpressionAttributeValues(eav);
        List<Student> studentList = mapper.scan(Student.class, scanExpression);
        return studentList.size() == 0 ? null : studentList;
    }

    // Getting One student
    public Student getStudent(String studentId) {
        HashMap<String, AttributeValue> query = new HashMap<>();
        query.put(":attribute1", new AttributeValue().withS(studentId));
        DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
                .withIndexName("studentId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("studentId = :attribute1")
                .withExpressionAttributeValues(query);
        List<Student> studentList = mapper.query(Student.class, queryExpression);
        return studentList.size() == 0 ? null : studentList.get(0);
    }

//    public Student getStudent(String studentId) {
//        return mapper.load(Student.class, studentId);
//    }

    public Student addStudent(Student student) {
        if(student == null) {return null;}
        student.setJoiningDate(new Date().toString());
        mapper.save(student);
        return student;
    }

    public Student deleteStudent(String studentId) {
        Student student = getStudent(studentId);
        if(student == null) {return null;}
        mapper.delete(student);
        return student;
    }

    public Student updateStudent(String studentId, Student student) {
        deleteStudent(studentId);
        student.setStudentId(studentId);
        mapper.save(student);
        return student;
    }
//
//    // get all course enrolled
//    public List<Course> getEnrolledCourse(String studentId) {
//        HashMap<String, AttributeValue> query = new HashMap<>();
//        query.put(":attribute1", new AttributeValue().withS(studentId));
//        DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
//                .withIndexName("studentId-index")
//                .withConsistentRead(false)
//                .withKeyConditionExpression("studentId = :attribute1")
//                .withExpressionAttributeValues(query);
//        List<Course> courseList = mapper.query(Course.class, queryExpression);
//        return courseList.size() == 0 ? null : courseList;
//    }
//
//    // enroll a course
//    public Student enrollCourse(Long stuId, Course course) throws Exception{
//        Student student = student_Map.get(stuId);
//        ArrayList<Course> courselist = student.getCoursesEnrolled();
//        for(int i =0; i< courselist.size(); i++){
//            if(courselist.get(i).getCourseId().equals(course.getCourseId())){
//                throw new Exception("already enrolled");
//            }
//        }
//        courselist.add(course);
//        student.setCoursesEnrolled(courselist);
//        student_Map.put(stuId, student);
//        return student;
//
//    }
//
//    // withdraw a course
//    public Student withdrawCourse(Long stuId, Course course) throws Exception{
//        Student student = student_Map.get(stuId);
//        ArrayList<Course> courselist = student.getCoursesEnrolled();
//        for(int i =0; i< courselist.size(); i++){
//            if(courselist.get(i).getCourseId().equals(course.getCourseId())){
//                courselist.remove(course);
//            }
//            else{
//                throw new Exception("you dont enroll this course;");
//            }
//        }
//        student.setCoursesEnrolled(courselist);
//        student_Map.put(stuId, student);
//        return student;
//    }
//
//
//    // change pic
//    public Student updateImage(Long stuId, String newImage){
//        Student student = student_Map.get(stuId);
//        student.setImage(newImage);
//        student_Map.put(stuId, student);
//        return student;
//    }


}
