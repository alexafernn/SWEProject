package com.alexandrafernandez.sweproject;

/**
 * Pet Data Class
 * This class serves as the Model for the Pet Class
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class PetData {

    /**
     * Animal information strings
     */
    String name, id;

    /**
     * Pet Data constructor
     * Initializes the data for a pet entry
     * @param name the name of the pet
     * @param id the id of the pet, given by the server
     */
    public PetData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * to String method
     * Prints pet name for list view display purposes
     * @return start and end dat
     */
    public String toString() {
        return name;
    }

    /**
     * Additional testing methods
     */
    public String getId() { return id; }
}
