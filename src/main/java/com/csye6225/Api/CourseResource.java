package com.csye6225.Api;

/**
 * @author Rexus
 * @date 2019-10-18
 */


import com.csye6225.Service.CourseService;
import com.csye6225.datamodel.Course;

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

@Path("courses")
public class CourseResource {
    private CourseService courseService = new CourseService();
    public CourseResource(){

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourseByProfessor(
            @QueryParam("professor") String professor) {

        if (professor == null) {
            return courseService.getAllCourses();
        }
        return courseService.getAllCoursesByProfessor(professor);

    }

    // ... webapi/course/1
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getStudent(@PathParam("courseId") Long courseId) {
        System.out.println("Student Resource: Looking for: " + courseId);
        return courseService.getCourse(courseId);
    }


    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") Long courseId) {
        return courseService.deleteCourse(courseId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course) {
        return courseService.addCourse(course.getName(), course.getRoster(), course.getLectures(), course.getProfessor());
    }

    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateProfessor(@PathParam("courseId") Long courseId,
                                  Course course) {
        return courseService.updateCourse(courseId, course);
    }

    //




}
