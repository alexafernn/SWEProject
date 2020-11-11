package com.alexandrafernandez.sweproject;

import android.content.Context;

public class ScreenSize{

    int width, height;

    public ScreenSize(Context context){
        width= context.getResources().getDisplayMetrics().widthPixels;
        height= context.getResources().getDisplayMetrics().heightPixels;
    }
}
