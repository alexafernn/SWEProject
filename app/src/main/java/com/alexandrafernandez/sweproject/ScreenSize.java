package com.alexandrafernandez.sweproject;

import android.content.Context;

public class ScreenSize{

    int width, height, labelTextSize, editTextSize, switchTextSize, buttonTextSize;

    public ScreenSize(Context context){
        width= context.getResources().getDisplayMetrics().widthPixels;
        height= context.getResources().getDisplayMetrics().heightPixels;

        labelTextSize = height/60;
        editTextSize = (int) (0.8*labelTextSize);
        switchTextSize = (int) (0.6*labelTextSize);
        buttonTextSize = (int) (0.6*labelTextSize);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getLabelTextSize(){
        return labelTextSize;
    }

    public int getEditTextSize(){
        return editTextSize;
    }

    public int getSwitchTextSize(){
        return switchTextSize;
    }

    public int getButtonTextSize(){
        return buttonTextSize;
    }
}
