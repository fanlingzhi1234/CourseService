package com.csye6225.Service;

/**
 * @author Rexus
 * @date 2019-10-18
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.csye6225.datamodel.DynamoDbConnector;
import com.csye6225.datamodel.InMemoryDatabase;
import com.csye6225.datamodel.Professor;

public class ProfessorService {

    private static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

    public ProfessorService() {
    }

    // Getting a list of all professor
    // GET "..webapi/professors"
    public List<Professor> getAllProfessors() {
        //Getting the list
        ArrayList<Professor> list = new ArrayList<>();
        list.addAll(prof_Map.values());
//        for (Professor prof : prof_Map.values()) {
//            list.add(prof);
//        }
        return list ;
    }

    // Adding a professor
    public Professor addProfessor(String firstName, String lastName, String department, String joiningDate) {
        // Next Id
        long nextAvailableId = prof_Map.size() + 1;

        //Create a Professor Object
        Professor prof = new Professor(firstName+lastName, firstName , lastName,
                department, joiningDate.toString());

        prof_Map.put(nextAvailableId, prof);
        return prof;
    }


    // Getting One Professor
    public Professor getProfessor(Long profId) {

        //Simple HashKey Load
        Professor prof = prof_Map.get(profId);

        return prof;
    }

    // Deleting a professor
    public Professor deleteProfessor(Long profId) {
        Professor deletedProfDetails = prof_Map.get(profId);
        prof_Map.remove(profId);
        return deletedProfDetails;
    }

    // Updating Professor Info
    public Professor updateProfessorInformation(Long profId, Professor prof) {
        Professor oldProfObject = prof_Map.get(profId);
        profId = oldProfObject.getId();
        prof.setId(profId);
        return prof;
    }

    // Get professors in a department
    public List<Professor> getProfessorsByDepartment(String department) {
        //Getting the list
        ArrayList<Professor> list = new ArrayList<>();
        for (Professor prof : prof_Map.values()) {
            if (prof.getDepartment().equals(department)) {
                list.add(prof);
            }
        }
        return list ;
    }

    // Get professors for a year with a size limit

}
