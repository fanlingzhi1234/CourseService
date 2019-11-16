package com.csye6225.datamodel;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.List;

/**
 * @author Rexus
 * @date 2019-10-23
 */

@DynamoDBTable(tableName = "Program")
public class Program {
    private String Id;
    private String programId;
    private String name;
    private List<String> courses;
    private List<String> professors;

    public Program(){

    }
    public Program(String programId, String name){
        this.programId = programId;
        this.name = name;
    }

    public Program(String programId, String name, List<String> courses, List<String> professors) {
        this.programId = programId;
        this.name = name;
        this.courses = courses;
        this.professors = professors;

    }
    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    @DynamoDBIndexHashKey(attributeName = "programId", globalSecondaryIndexName = "programId-index")
    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }
    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "courses")
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
    @DynamoDBAttribute(attributeName = "professors")
    public List<String> getProfessors() {
        return professors;
    }

    public void setProfessors(List<String> professors) {
        this.professors = professors;
    }
}
