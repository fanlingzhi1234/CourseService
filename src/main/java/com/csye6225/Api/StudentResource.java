package com.csye6225.Api;

import com.csye6225.Service.StudentService;
import com.csye6225.datamodel.Student;

/**
 * @author Rexus
 * @date 2019-10-18
 */


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("students")
public class StudentResource {
    private StudentService studentService = new StudentService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByProgram(
            @QueryParam("program") String program) {

        if (program == null) {
            return studentService.getAllStudents();
        }
        return studentService.getStudentByProgram(program);

    }

    // ... webapi/student/1
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") Long studentId) {
        System.out.println("Student Resource: Looking for: " + studentId);
        return studentService.getStudent(studentId);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student) {

        return studentService.addStudent(student.getFirstName(), student.getLastName(), student.getImage(), student.getProgram());
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateProfessor(@PathParam("studentId") Long studentId,
                                     Student student) {
        return studentService.updateStudentInfo(studentId, student);
    }
}
