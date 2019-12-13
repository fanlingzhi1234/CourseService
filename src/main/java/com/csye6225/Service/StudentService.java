package com.csye6225.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.csye6225.datamodel.DynamoDbConnector;
import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.SNSClient;
import com.csye6225.datamodel.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author Rexus
 * @date 2019-10-18
 */
public class StudentService {


    private static DynamoDbConnector dynamoDb;
    private DynamoDBMapper mapper;
    private AmazonSNS snsClient;
    public StudentService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
        snsClient = SNSClient.getClient();
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
    //     enroll a course
    public boolean enrollCourse(String studentId, String courseId){

        Student stu = getStudent(studentId);
        Course course = mapper.load(Course.class, courseId);
        if(stu == null || stu.getEnrolledCourses().size() >= 3){
            return false;
        }
        List<String> courselist = stu.getEnrolledCourses();
        courselist.add(courseId);
        stu.setEnrolledCourses(courselist);
        SubscribeRequest subscribeRequest = new SubscribeRequest(course.getNotificationTopic(), "email", stu.getEmail());
        snsClient.subscribe(subscribeRequest);
        this.updateStudent(studentId, stu);
        return true;
    }

//
    // get all course enrolled
    public List<String> getEnrolledCourse(String studentId) {
        Student stu = getStudent(studentId);
        return stu.getEnrolledCourses();
    }
//

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
