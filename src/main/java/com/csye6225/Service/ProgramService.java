package com.csye6225.Service;

import com.csye6225.datamodel.Course;
import com.csye6225.datamodel.InMemoryDatabase;
import com.csye6225.datamodel.Professor;
import com.csye6225.datamodel.Program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-23
 */
public class ProgramService {
    static HashMap<Long, Program> program_Map = InMemoryDatabase.getProgramDB();


    public ProgramService() {
    }

    // Getting a list of all program
    // GET "..webapi/program"
    public List<Program> getAllPrograms() {
        //Getting the list
        ArrayList<Program> list = new ArrayList<>();
        list.addAll(program_Map.values());
//        for (Program program : program_Map.values()) {
//            list.add(program);
//        }
        return list ;
    }

    // Getting One program
    public Program getProgram(Long programID) {

        //Simple HashKey Load
        Program program = program_Map.get(programID);
        System.out.println("Item retrieved:");
        System.out.println(program.toString());

        return program;
    }

    // Adding a program
    public Program addProgram(String name) {
        // Next Id
        long nextAvailableId = program_Map.size() + 1;

        //Create a Professor Object
        Program program = new Program(nextAvailableId, name);

        program_Map.put(nextAvailableId, program);
        return program;
    }

    // Deleting a program
    public Program deleteProgram(Long programId) {
        Program deletedProgramDetails = program_Map.get(programId);
        program_Map.remove(programId);
        return deletedProgramDetails;
    }


    // Updating Program Info
    public Program updateProgramInformation(Long programId, Program updateProgram) {
        Program oldProgramObj = program_Map.get(programId);
        oldProgramObj.setName(updateProgram.getName());
        oldProgramObj.setCourses(updateProgram.getCourses());
        oldProgramObj.setProfessors(updateProgram.getProfessors());
        program_Map.put(programId, oldProgramObj);
        return oldProgramObj;
    }

    // get professor list
    public ArrayList<Professor> getProfessorsOfProgram(Long programId){
        Program oldProgramObj = program_Map.get(programId);
        return oldProgramObj.getProfessors();
    }

    // Adding professor
    public Program addProfessor (Long programId, Professor professor) throws Exception {
        Program oldProgramObj = program_Map.get(programId);
        ArrayList<Professor> proflist = oldProgramObj.getProfessors();
        for (Professor prof : proflist) {
            if (prof.getProfessorId().equals(professor.getProfessorId())) {
                throw new Exception("already has this professor");
            }
        }
        proflist.add(professor);
        oldProgramObj.setProfessors(proflist);
        program_Map.put(programId, oldProgramObj);
        return oldProgramObj;
    }

    //remove professor from list
    public Program removeProfessor(Long programId, Professor professor) throws Exception{
        Program oldProgramObj = program_Map.get(programId);
        ArrayList<Professor> proflist = oldProgramObj.getProfessors();
        for (Professor prof : proflist) {
            if (prof.getProfessorId().equals(professor.getProfessorId())) {
                proflist.remove(professor);
            }
            else{
                throw new Exception("we dont have this professor yet;");
            }
        }
        oldProgramObj.setProfessors(proflist);
        program_Map.put(programId, oldProgramObj);
        return oldProgramObj;
    }

    // get course list
    public ArrayList<Course> getCoursesOfProgram(Long programId){
        Program oldProgramObj = program_Map.get(programId);
        return oldProgramObj.getCourses();
    }
    // Adding course
    public Program addCourse (Long programId, Course course) throws Exception {
        Program oldProgramObj = program_Map.get(programId);
        ArrayList<Course> courseList = oldProgramObj.getCourses();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId().equals(course.getCourseId())) {
                throw new Exception("already has this course");
            }
        }
        courseList.add(course);
        oldProgramObj.setCourses(courseList);
        program_Map.put(programId, oldProgramObj);
        return oldProgramObj;
    }

    //remove course from list
    public Program removeCourse(Long programId, Course course) throws Exception{
        Program oldProgramObj = program_Map.get(programId);
        ArrayList<Course> courseList = oldProgramObj.getCourses();
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId().equals(course.getCourseId())) {
                courseList.remove(course);
            }
            else{
                throw new Exception("we dont have this professor yet;");
            }
        }
        oldProgramObj.setCourses(courseList);
        program_Map.put(programId, oldProgramObj);
        return oldProgramObj;
    }






}
