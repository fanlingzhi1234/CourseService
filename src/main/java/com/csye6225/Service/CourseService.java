package com.csye6225.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.DynamoDbConnector;
import com.csye6225.datamodel.SNSClient;


import java.util.HashMap;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class CourseService {
    private static DynamoDbConnector dynamoDb;
    private DynamoDBMapper mapper;
    private AmazonSNS snsClient;

    public CourseService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
        snsClient = SNSClient.getClient();
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
        CreateTopicRequest createTopicRequest = new CreateTopicRequest(course.getCourseId() + "Topic");
        CreateTopicResult result = snsClient.createTopic(createTopicRequest);
        course.setNotificationTopic(result.getTopicArn());
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

    public Course addStudent(String courseId, String studentId){
        Course course = getCourse(courseId);
        List<String> roster = course.getRoster();
        roster.add(studentId);
        this.updateCourse(courseId, course);
        return course;
    }

    // remove student
    public Course deleteStudent(String courseId, String studentId){

        Course course = getCourse(courseId);
        List<String> roster = course.getRoster();
        if(!roster.contains(studentId)){
            System.out.println("this student didn't register this course;");
            return course;
        }
        roster.remove(studentId);
        this.updateCourse(courseId, course);
        return course;
    }




}
