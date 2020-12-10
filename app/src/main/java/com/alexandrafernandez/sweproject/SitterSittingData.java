package com.alexandrafernandez.sweproject;

/**
 * Owner Sitting Data Class
 * This class serves as the Model for the Owner Sitting  Class
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */

public class SitterSittingData
{
    /**
     * Sitter Sitting information strings
     * (based off what it says on the wiki);
     */
    String sittingID, startDateTime, endDateTime, ownerName;

    /**
     * Sitter Sitting Data constructor
     * Initializes the data for a owner sitting  entry
     */
    public SitterSittingData(String sittingID, String startDateTime, String endDateTime, String ownerName)
    {
        this.sittingID = sittingID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.ownerName = ownerName;
    }


    /**
     * to String method
     * Prints location, startTime, endTime, details, sitterName for list view display purposes
     * @return the print statement
     */
    public String toString() {
        return("Start:   " + startDateTime
                + "\nEnd:     " + endDateTime
                + "\nOwner: " + ownerName);
    }

}
