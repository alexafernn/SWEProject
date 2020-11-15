package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

public class Sitting {

    private int numberOfSittings;
    private String startDate;
    private String endDate;
    private boolean allMyPets;
    private boolean mustOccurAtMyLocation;
    private String notesForSitter;

    public Sitting(Context context)
    {

    }

    public Sitting(String startDate, String endDate, boolean allMyPets, boolean mustOccurAtMyLocation, String notesForSitter) /*select specific pets*/
    {
        this.startDate=startDate;
        this.endDate=endDate;
        this.allMyPets=allMyPets;
        this.mustOccurAtMyLocation = mustOccurAtMyLocation;
        this.notesForSitter=notesForSitter;
    }

    public void updateNumberOfSittings()
    {
        numberOfSittings++;


    }

    public int getNumberOfSittings()
    {
        return numberOfSittings;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public boolean isAllMyPets()
    {
        return allMyPets;
    }

    public boolean isMustOccurAtMyLocation()
    {
        return mustOccurAtMyLocation;
    }

    public String getNotesForSitter()
    {
        return notesForSitter;
    }

    @Override
    public String toString() {
        return startDate;
//        return "Sitting{" +
//                "numberOfSittings=" + numberOfSittings +
//                ", startDate='" + startDate + '\'' +
//                ", endDate='" + endDate + '\'' +
//                ", allMyPets=" + allMyPets +
//                ", mustOccurAtMyLocation=" + mustOccurAtMyLocation +
//                ", notesForSitter='" + notesForSitter + '\'' +
//                '}';
    }
}
