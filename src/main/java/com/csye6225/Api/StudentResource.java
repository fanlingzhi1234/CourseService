package com.csye6225.Api;

/**
 * @author Rexus
 * @date 2019-10-18
 */

import com.csye6225.Service.CourseService;
import com.csye6225.Service.StudentService;
import com.csye6225.datamodel.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("students")
public class StudentResource {
    StudentService studentService = new StudentService();
    CourseService courseService = new CourseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GET
    @Path("/department/{department}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByProgram(
            @QueryParam("department") String department) {

        if (department == null) {
            return studentService.getAllStudents();
        }
        return studentService.getStudentByDepartment(department);

    }

    // ... webapi/student/1
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") String studentId) {
        System.out.println("Student Resource: Looking for: " + studentId);
        return studentService.getStudent(studentId);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") String studentId) {
        return studentService.deleteStudent(studentId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student) {

        return studentService.addStudent(student);
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateProfessor(@PathParam("studentId") String studentId,
                                     Student student) {
        return studentService.updateStudent(studentId, student);
    }
    @POST
    @Path("/{studentId}/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean enrollCourse(@PathParam("studentId") String studentId, @FormParam("courseId") String courseId) {
        return studentService.enrollCourse(studentId, courseId);
    }


}
