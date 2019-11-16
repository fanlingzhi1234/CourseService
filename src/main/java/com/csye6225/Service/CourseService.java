package com.csye6225.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.DynamoDbConnector;


import java.util.HashMap;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class CourseService {
    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;

    public CourseService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }


    // Getting a list of all course
    public List<Course> getAllCourses() {
        return mapper.scan(Course.class, new DynamoDBScanExpression());
    }



    // Getting One Course
    public Course getCourse(String courseId) {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(courseId));
        DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
                .withIndexName("courseId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("courseId = :v1")
                .withExpressionAttributeValues(eav);
        List<Course> courseList= mapper.query(Course.class, queryExpression);
        return courseList.size() == 0 ? null : courseList.get(0);
    }
//    public Course getCourse2(String courseId) {
//        return mapper.load(Course.class, courseId);
//    }



    // Adding a Course
    public Course addCourse(Course course) {
        if (course == null) {return null;}
        mapper.save(course);
        return course;
    }

    // Deleting a course
    public Course deleteCourse(String courseId) {
        Course course = getCourse(courseId);
        if (course == null) {return null;}
        mapper.delete(course);
        return course;
    }

    // Updating the course info
    public Course updateCourse(String courseId, Course course) {
        deleteCourse(courseId);
        course.setCourseId(courseId);
        mapper.save(course);
        return course;
    }

    // Publish a message in board;

    // add lecture to list;

    // change professor

    // change TA

    // change Roaster

    // add enrolled student
//    public Course addStudent(Long courseId, Student student){
//        Course course = course_Map.get(courseId);
//        ArrayList<Student> studentlist = course.getEnrolledStudent();
//        for(int i =0; i< studentlist.size(); i++){
//            if(studentlist.get(i).getStudentId().equals(student.getStudentId())){
//                throw new Exception("already enrolled");
//            }
//        }
//        studentlist.add(student);
//        course.setEnrolledStudent(studentlist);
//        course_Map.put(courseId, course);
//        return course;
//    }
//
//    // remove student
//    public Course withdrawStudent(Long courseId, Student student){
//
//        Course course = course_Map.get(courseId);
//        ArrayList<Student> studentlist = course.getEnrolledStudent();
////        for(int i =0; i< studentlist.size(); i++){
////            if(studentlist.get(i).getStudentId().equals(student.getStudentId())){
//                studentlist.remove(student);
////            }
////            else{
////                throw new Exception("you dont enroll this course;");
////            }
////        }
//        course.setEnrolledStudent(studentlist);
//        course_Map.put(courseId, course);
//        return course;
//    }




}
