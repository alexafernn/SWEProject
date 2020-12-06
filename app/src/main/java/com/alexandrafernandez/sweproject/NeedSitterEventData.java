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
    String start_dateTime, end_dateTime, id;

    /**
     * Need Sitter Event Data constructor
     * Initializes the data for a sitting event
     * @param id the id of the job
     * @param start_dateTime start date of job
     * @param end_dateTime end date of job
     */
    public NeedSitterEventData(String start_dateTime, String end_dateTime, String id) {
        this.start_dateTime = start_dateTime;
        this.end_dateTime = end_dateTime;
        this.id = id;
    }

    /**
     * to String method
     * Prints start date and end date for list view display purposes
     * @return start and end dat
     */
    public String toString() {
        return start_dateTime + " - " + end_dateTime;
    }

    /**
     * Additional testing methods
     */
    public String getId() { return id; }
}
