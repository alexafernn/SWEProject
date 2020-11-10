package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Pets extends AppCompatActivity {
    ArrayList<Pet> petList;
    TextView[] pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);
        setTitle("My Pets");

        /*
        petList = new ArrayList<Pet>();
        petList.add(new Pet("test"));
        pets = new TextView[petList.size()];

        GridLayout gridLayout = new GridLayout( this );
        gridLayout.setRowCount(8);
        gridLayout.setColumnCount(8);

        int x=0,y=0;

        for( int row = 0; row < 8; row++ ) {
            pets[row]= new TextView(this);
            pets[row].setHeight(50);
            pets[row].setWidth(300);
            pets[row].setTextSize((int) 30);
            pets[row].setText(petList.get(row).petName);
            pets[row].setEnabled(true);
            pets[row].setVisibility(View.VISIBLE);
            gridLayout.addView(pets[row], x, y);
            y+=50;
        }

        setContentView(gridLayout);

         */
    }

    public void addPettoList(Pet p)
    {
        petList.add(p);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pets_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.action_favorite:
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.add_pet_menu:
                startActivity(new Intent(this, AddPet.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
