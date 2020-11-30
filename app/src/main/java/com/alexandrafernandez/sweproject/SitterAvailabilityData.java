package com.alexandrafernandez.sweproject;

/**
 * Sitter Availability Class
 * This class serves as the Model for the Sitter Availability Class
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class SitterAvailabilityData {

    /**
     * Availability information strings
     */
    String startDate, endDate, notes;

    /**
     * Sitter Availability Data constructor
     * @param startDate start date of the availability
     * @param endDate end date of the availability
     * @param notes notes to owners about this availability
     */
    public SitterAvailabilityData(String startDate, String endDate, String notes) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    /**
     * to String method
     * Prints start and end date for list view display purposes
     * @return start and end dat
     */
    public String toString() {
        return startDate + " - " + endDate;
    }
}
