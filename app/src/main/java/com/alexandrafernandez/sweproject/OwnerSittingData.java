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
     public OwnerSittingData(String sittingID, String location, String startDateTime, String endDateTime, String petId, String details, String ownerName, String sitterName, float lat, float longitude,
                             boolean isAtOwnerLocation, boolean accepted, boolean canceled, boolean success)
     {
         this.sittingID = sittingID;
         this.location = location;
         this.startDateTime = startDateTime;
         this.endDateTime = endDateTime;
         this.petBeingSittedID = petId;
         this.details = details;
         this.ownerName = ownerName;
         this.sitterName = sitterName;
         this.lat = lat;
         this.longitude = longitude;
         this.isAtOwnerLocation = isAtOwnerLocation;
         this.accepted = accepted;
         this.canceled = canceled;
         this.success = success;
     }

    /**
     * to String method
     * Prints location, startTime, endTime, details, sitterName for list view display purposes
     * @return the print statement
     */
    public String toString() {
        return("appointment start: " + startDateTime
                + " appointment end: " + endDateTime
                + " location: " + location
                + " accepted: " + accepted);
    }


}
