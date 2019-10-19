package com.csye6225.datamodel;

/**
 * @author Rexus
 * @date 2019-10-18
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

    private static HashMap<Long, Professor> professorDB = new HashMap<> ();

    public static HashMap<Long, Professor> getProfessorDB() {
        return professorDB;
    }
}
