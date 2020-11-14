package com.alexandrafernandez.sweproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

public class Pets extends AppCompatActivity {
    ArrayList<Pet> petList;
    TextView[] pets;
    ListView pet_listview;
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);
        setTitle("My Pets");

        petList = new ArrayList<Pet>(); //change this to pull from server initially
        petList.add(new Pet("test1", "animal1"));
        petList.add(new Pet("test2", "animal2"));

        pet_listview = (ListView) findViewById(R.id.pet_listview);
        ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(this, android.R.layout.simple_list_item_1, petList);
        pet_listview.setAdapter(adapter);

        pet_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pet = petList.get(i);

                //TODO code below causes a crash when launching pet activity
                Intent intent = new Intent(getActivity(), Pet.class);
                intent.putExtra(pet.petName, "petToView.petName");
                intent.putExtra(pet.animalType, "petToView.animalType");
                //add more data here
                startActivity(intent);
                finish();

            }
        });


        /*
        petList.add(new Pet("test", null, null, null, null, null, null));
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

    public Activity getActivity(){
        return this;
    }

    public void goToPet(View view) {
        Intent i = new Intent(this, Pet.class);
        i.putExtra(pet.petName, "petToView.petName");
        i.putExtra(pet.animalType, "petToView.animalType");
        //add more data here
        startActivity(i);
        finish();
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
