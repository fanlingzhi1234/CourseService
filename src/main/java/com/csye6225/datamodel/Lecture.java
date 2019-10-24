package com.csye6225.datamodel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rexus
 * @date 2019-10-18
 */
public class Lecture {

    private String id;
    private String notes;
    private ArrayList<String> materials;

    public Lecture() {
    }

    public Lecture(String id, String notes, ArrayList<String> materials) {
        this.id = id;
        this.notes = notes;
        this.materials = materials;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id='" + id + '\'' +
                ", notes='" + notes + '\'' +
                ", materials=" + materials +
                '}';
    }
}
