package com.alexandrafernandez.sweproject;

public class PetData {

    String name, animal, other_type;
    boolean energetic, noisy, trained, inside_only;

    public PetData(String name, String animal, String other_type, boolean energetic, boolean noisy, boolean trained, boolean inside_only) {
        this.name = name;
        this.animal = animal;
        this.other_type = other_type;
        this.energetic = energetic;
        this.noisy = noisy;
        this.trained = trained;
        this.inside_only = inside_only;
    }

    public String toString() {
        return name;
    }
}
