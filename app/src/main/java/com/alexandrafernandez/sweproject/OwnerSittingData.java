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
public class OwnerSittingData
{
    /**
     * Owner Sitting information strings
     * (based off what it says on the wiki);
     */
     String location, startDateTime, endDateTime, petBeingSittedID, details, ownerName, sitterName, sittingID;

     float lat, longitude;

     boolean isAtOwnerLocation, accepted, canceled, success;


     //add params to this jDoc
    /**
     * Owner Sitting Data constructor
     * Initializes the data for a owner sitting  entry
    */
     public OwnerSittingData(String sittingID,  String startDateTime, String endDateTime,
                              boolean accepted, String sitterName)
     {
         this.sittingID = sittingID;
         this.startDateTime = startDateTime;
         this.endDateTime = endDateTime;
         this.accepted = accepted;
         this.sitterName = sitterName;
     }

    /**
     * to String method
     * Prints location, startTime, endTime, details, sitterName for list view display purposes
     * @return the print statement
     */
    public String toString() {
        return("Start:  " + startDateTime + "\nEnd:    " + endDateTime + "\nSitter: " + sitterName);
    }


}
