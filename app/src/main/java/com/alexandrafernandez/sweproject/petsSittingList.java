package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class petsSittingList extends AppCompatActivity
{

    PetData petData;
    /**
     * List managing the pets in reservation
     */
    ArrayList<PetData> petList;

    /**
     * List View for managing multiple content instances of pets in reservation
     */
    ListView petReservation_listview;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;

    /**
     * Activity and View data
     */
    private Context context;

    /**
     * Pet information to be selected and accessed after assignment
     */
    PetData pet;

    TextView noPetsInReservation;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets_to_sit);
        setTitle("Pets in Reservation");


        petList = new ArrayList<PetData>();
        petList.add(new PetData("Cocco", "id number"));
        petReservation_listview = (ListView) findViewById(R.id.petsToSit_listview);
        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                return textView;

            }
        };
        petReservation_listview.setAdapter(adapter);


        // do something when u click on the sitting
        context = this;
        petReservation_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                petData = petList.get(i);
                System.out.println("i is = " + i);
                Intent intent = new Intent(context, petBeingSat.class);
                //intent.putExtra("position_in_list", i);
                startActivity(intent);

                finish();
            }
        });


//        noPetsInReservation = (TextView) findViewById(R.id.noPetsInReservation);
//
//        pref = PreferenceManager.getDefaultSharedPreferences(this);
//
//        petReservation_listview = (ListView) findViewById(R.id.petsToSit_listview);
//        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList);
//        petReservation_listview.setAdapter(adapter);
//
//        context = this;

//        petReservation_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                pet = petList.get(i);
//
//                Intent intent = new Intent(context, Pet.class);
//                intent.putExtra("pet_id", pet.id);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}
