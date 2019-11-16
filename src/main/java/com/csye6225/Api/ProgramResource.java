package com.csye6225.Api;

/**
 * @author Rexus
 * @date 2019-10-24
 */

import com.csye6225.Service.ProgramService;
import com.csye6225.datamodel.Program;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//@Path("program")
public class ProgramResource {
    private ProgramService programService = new ProgramService();

    public ProgramResource(){

    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Program> getPrograms() {
//        return programService.getAllPrograms();
//    }
//
//
//    // ... webapi/program/1
//    @GET
//    @Path("/{programId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Program getprogram(@PathParam("programId") Long programId) {
//        System.out.println("Student Resource: Looking for: " + programId);
//        return programService.getProgram(programId);
//    }
//
//
//    @DELETE
//    @Path("/{programId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Program deleteProgram(@PathParam("programId") Long programId) {
//        return programService.deleteProgram(programId);
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Program addProgram(Program program) {
//        return programService.addProgram(program.getName());
//    }
//
//    @PUT
//    @Path("/{programId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Program updateProfessor(@PathParam("programId") Long programId,
//                                   Program program) {
//        return programService.updateProgramInformation(programId, program);
//    }


}
