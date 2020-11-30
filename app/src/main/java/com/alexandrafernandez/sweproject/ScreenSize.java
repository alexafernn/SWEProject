package com.alexandrafernandez.sweproject;

import android.content.Context;

/**
 * Screen Size Class
 * This class allows the program to be resized based on the dimensions of the screen used
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class ScreenSize{

    /**
     * Variables used to manage screen size and component sizes
     */
    int width, height, labelTextSize, editTextSize, switchTextSize, buttonTextSize;

    /**
     * Screen Size Construtor
     * @param context Activity and View data of calling class
     */
    public ScreenSize(Context context){

        //obtain screen dimensions
        width= context.getResources().getDisplayMetrics().widthPixels;
        height= context.getResources().getDisplayMetrics().heightPixels;

        //set size dimensions of view components
        labelTextSize = height/60;
        editTextSize = (int) (0.8*labelTextSize);
        switchTextSize = (int) (0.6*labelTextSize);
        buttonTextSize = (int) (0.6*labelTextSize);
    }

    /**
     * Get Width method
     * @return width of the screen
     */
    public int getWidth(){
        return width;
    }

    /**
     * Get Height method
     * @return height of the screen
     */
    public int getHeight(){
        return height;
    }

    /**
     * Get Label Text Size method
     * Adjusts the text size of TextViews as appropriate to screen size
     * @return label text size
     */
    public int getLabelTextSize(){
        return labelTextSize;
    }

    /**
     * Get Edit Text Size method
     * Adjusts the text size of EditText views as appropriate to screen size
     * @return edit text size
     */
    public int getEditTextSize(){
        return editTextSize;
    }

    /**
     * Get Switch Text Size method
     * Adjusts the text size of Switches as appropriate to screen size
     * @return switch text size
     */
    public int getSwitchTextSize(){
        return switchTextSize;
    }

    /**
     * Get Button Text Size method
     * Adjusts the text size of Buttons as appropriate to screen size
     * @return button text size
     */
    public int getButtonTextSize(){
        return buttonTextSize;
    }
}
