package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Sitting implements Serializable/*implements Parcelable*/ {

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

    protected Sitting(Parcel in) {
        numberOfSittings = in.readInt();
        startDate = in.readString();
        endDate = in.readString();
        allMyPets = in.readByte() != 0;
        mustOccurAtMyLocation = in.readByte() != 0;
        notesForSitter = in.readString();
    }

//    public static final Creator<Sitting> CREATOR = new Creator<Sitting>() {
//        @Override
//        public Sitting createFromParcel(Parcel in) {
//            return new Sitting(in);
//        }
//
//        @Override
//        public Sitting[] newArray(int size) {
//            return new Sitting[size];
//        }
//    };

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
        StringBuilder sb = new StringBuilder();
        sb.append("Start Date: ").append(startDate).append(System.getProperty("line.separator"));
        sb.append("End Date: ").append(endDate).append(System.getProperty("line.separator"));
        sb.append("All of Owners Pets: ").append(allMyPets).append(System.getProperty("line.separator"));
        sb.append("Must Occur at Owner's House: ").append(mustOccurAtMyLocation).append(System.getProperty("line.separator"));
        sb.append("Notes for Sitter: ").append(notesForSitter);
        return sb.toString();
        //return startDate;
//        return
////                "numberOfSittings=" + numberOfSittings +
//                "start Date='" + startDate + '\'' +
//                "end Date='" + endDate + '\'' +
//                ", all My Pets=" + allMyPets +
//                ", mustOccurAtMyLocation=" + mustOccurAtMyLocation +
//                ", notesForSitter='" + notesForSitter + '\''
//                ;
    }


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//   // @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.startDate);
//        dest.writeString(this.endDate);
//        dest.writeByte((byte) (allMyPets ? 1:0));
//        dest.writeByte((byte) (mustOccurAtMyLocation ?1:0));
//        //dest.writeBoolean(this.allMyPets);
//        //dest.writeBoolean(this.mustOccurAtMyLocation);
//        dest.writeString(this.notesForSitter);
//    }
}
