package com.alexandrafernandez.sweproject;

public class NeedSitterEventData {

    String start_date, start_time, end_date, end_time, other_notes;
    boolean allPets, stayHome;

    public NeedSitterEventData(String start_date, String start_time, String end_date, String end_time, boolean allPets, boolean stayHome, String other_notes) {
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_date = end_date;
        this.end_time = end_time;
        this.allPets = allPets;
        this.stayHome = stayHome;
        this.other_notes = other_notes;
    }

    public String toString() {
        return start_date + " - " + end_date;
    }
}
