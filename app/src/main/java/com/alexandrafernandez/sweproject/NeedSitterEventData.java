package com.alexandrafernandez.sweproject;

/**
 * Need Sitter Event Data Class
 * This class allows serves as the model for the Need Sitter Event class
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class NeedSitterEventData {

    /**
     * Date, time, and notes variables
     */
    String start_date, start_time, end_date, end_time, other_notes;

    /**
     * Boolean variables for saving user choices
     */
    boolean allPets, stayHome;

    /**
     * Need Sitter Event Data constructor
     * Initializes the data for a sitting event
     * @param start_date start date of the sitting event
     * @param start_time start time of the sitting event
     * @param end_date end date of the sitting event
     * @param end_time end time of the sitting event
     * @param allPets whether all pets need to be sat
     * @param stayHome whether the pets will stay at the owners home
     * @param other_notes user entered details about the sitting event
     */
    public NeedSitterEventData(String start_date, String start_time, String end_date, String end_time, boolean allPets, boolean stayHome, String other_notes) {
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_date = end_date;
        this.end_time = end_time;
        this.allPets = allPets;
        this.stayHome = stayHome;
        this.other_notes = other_notes;
    }

    /**
     * to String method
     * Prints start date and end date for list view display purposes
     * @return start and end dat
     */
    public String toString() {
        return start_date + " - " + end_date;
    }
}
