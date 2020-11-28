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
    String name, animal, other_type;

    /**
     * Boolean variables for saving pet preferences
     */
    boolean energetic, noisy, trained, inside_only;

    /**
     * Pet Data constructor
     * Initializes the data for a pet entry
     * @param name the name of the pet
     * @param animal the species of the pet
     * @param other_type custom species
     * @param energetic is the pet energetic
     * @param noisy is the pet noisy
     * @param trained is the pet trained
     * @param inside_only does the pet only stay at owner's home
     */
    public PetData(String name, String animal, String other_type, boolean energetic, boolean noisy, boolean trained, boolean inside_only) {
        this.name = name;
        this.animal = animal;
        this.other_type = other_type;
        this.energetic = energetic;
        this.noisy = noisy;
        this.trained = trained;
        this.inside_only = inside_only;
    }

    /**
     * to String method
     * Prints pet name for list view display purposes
     * @return start and end dat
     */
    public String toString() {
        return name;
    }
}
