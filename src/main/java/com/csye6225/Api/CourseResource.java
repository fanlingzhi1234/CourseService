package com.csye6225.Api;

/**
 * @author Rexus
 * @date 2019-10-18
 */


import com.csye6225.Service.CourseService;
import com.csye6225.datamodel.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("courses")
public class CourseResource {
    CourseService courseService = new CourseService();
    public CourseResource(){

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Course> getCourseByProfessor(
//            @QueryParam("professor") String professor) {
//
//        if (professor == null) {
//            return courseService.getAllCourses();
//        }
//        return courseService.getAllCoursesByProfessor(professor);
//
//    }

    // ... webapi/course/1
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getStudent(@PathParam("courseId") String courseId) {
        System.out.println("Course Resource: Looking for: " + courseId);
        return courseService.getCourse(courseId);
    }


    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String courseId) {
        return courseService.deleteCourse(courseId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateProfessor(@PathParam("courseId") String courseId,
                                  Course course) {
        return courseService.updateCourse(courseId, course);
    }




}
