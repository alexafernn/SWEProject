package com.alexandrafernandez.sweproject;

public class Pet {
    public String petName;
    public String animalType;
    //gonna need something to store the image of pet
    boolean energetic;
    boolean noisy;
    boolean trained;
    boolean insideOnly;
    String otherInfo;

    public Pet(String petName, String animalType, boolean energetic, boolean noisy, boolean trained, boolean insideOnly, String otherInfo)
    {
        this.petName = petName;
        this.animalType=animalType;
        this.energetic= energetic;
        this.noisy=noisy;
        this.trained = trained;
        this.insideOnly = insideOnly;
        this.otherInfo = otherInfo;
    }


    @Override
    public String toString()
    {
        return("Pet Name:"+this.petName+
                " animal Type:"+this.animalType +
                " Energetic:"+ this.energetic+
                " Noisy:"+ this.noisy+
                " trained:"+this.trained+
                " insideOnly:"+this.insideOnly+
                " otherInfo:"+this.otherInfo);
    }

    public String getPetName()
    {
        return this.petName;
    }
}

