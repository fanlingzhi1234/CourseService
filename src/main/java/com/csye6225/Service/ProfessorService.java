package com.csye6225.Service;

/**
 * @author Rexus
 * @date 2019-10-18
 */
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.datamodel.DynamoDbConnector;
import com.csye6225.datamodel.Professor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProfessorService {

    static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;
    public ProfessorService() {
        dynamoDb = new DynamoDbConnector();
        dynamoDb.init();
        mapper = new DynamoDBMapper(dynamoDb.getClient());
    }

    // Getting a list of all professor
    public List<Professor> getAllProfessors() {
        return mapper.scan(Professor.class, new DynamoDBScanExpression());
    }

    // Get professors in a department
    public List<Professor> getProfessorsByDepartment(String department) {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(department));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("department = :v1")
                .withExpressionAttributeValues(eav);
        List<Professor> professorList = mapper.scan(Professor.class, scanExpression);
        return professorList.size() == 0 ? null : professorList;
    }

    // Getting One Professor
    public Professor getProfessor(String profId) {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(profId));
        DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
                .withIndexName("professorId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("professorId = :v1")
                .withExpressionAttributeValues(eav);
        List<Professor> professorList = mapper.query(Professor.class, queryExpression);
        for(Professor a : professorList){
            System.out.println(a.getFirstName());
        }
        return professorList.size() == 0 ? null : professorList.get(0);
    }

    public Professor getProfessorById(String profId){
        return mapper.load(Professor.class, profId);
    }

    // Adding a professor
    public Professor addProfessor(Professor prof) {
        if(prof == null) {return null;}
        prof.setJoiningDate(new Date().toString());
        mapper.save(prof);
        return prof;
    }

    // Deleting a professor
    public Professor deleteProfessor(String profId) {
        Professor professor = getProfessor(profId);
        if(professor == null) {return null;}
        mapper.delete(professor);
        return professor;
    }

    // Updating Professor Info
    public Professor updateProfessorInformation(String profId, Professor prof) {
        Professor professor = getProfessor(profId);
        if(professor == null) {return null;}
        deleteProfessor(profId);
        prof.setProfessorId(profId);
        mapper.save(prof);
        return prof;
    }




}
