package com.csye6225.Api;

/**
 * @author Rexus
 * @date 2019-10-18

 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.csye6225.datamodel.Professor;
import com.csye6225.Service.ProfessorService;
import java.util.List;

@Path("professors")
public class ProfessorResource {

    ProfessorService profService = new ProfessorService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getAllProfessors() {
        return profService.getAllProfessors();
    }

    @GET
    @Path("/department/{department}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department) {
        System.out.println("Professor Resource looking for:" + department);
        if (department == null) {
            return profService.getAllProfessors();
        }
        return profService.getProfessorsByDepartment(department);
    }

    @GET
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") String professorId) {
        System.out.println("Professor Resource: Looking for: " + professorId);
        return profService.getProfessor(professorId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor addProfessor(Professor prof) {
        return profService.addProfessor(prof);
    }

    @DELETE
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor deleteProfessor(@PathParam("professorId") String professorId) {
        return profService.deleteProfessor(professorId);
    }

    @PUT
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("professorId") String professorId,
                                     Professor prof) {
        return profService.updateProfessorInformation(professorId, prof);
    }

}
