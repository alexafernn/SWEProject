package com.alexandrafernandez.sweproject;

public class SitterAvailabilityData {

    String startDate, endDate, notes;

    public SitterAvailabilityData(String startDate, String endDate, String notes) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    public String toString() {
        return startDate + " - " + endDate;
    }
}
