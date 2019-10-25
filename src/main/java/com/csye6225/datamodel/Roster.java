package com.csye6225.datamodel;

/**
 * @author Rexus
 * @date 2019-10-23
 */
public class Roster {
    private String id;
    private String date;
    private String time;
    private String room;

    public Roster(){

    }

    public Roster(String id, String date, String time, String room) {

        this.id = id;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Roster{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
