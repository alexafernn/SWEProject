package com.alexandrafernandez.sweproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import java.util.Arrays;

public class Pets extends AppCompatActivity {
    ArrayList<PetData> petList;
    TextView[] pets;
    ListView pet_listview;
    PetData pet;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);
        setTitle("My Pets");

        petList = new ArrayList<PetData>(); //change this to pull from server initially
        petList.add(new PetData("test1", "animal1"));
        petList.add(new PetData("test2", "animal2"));
        Log.w("MA", "test print");
        Log.w("MA", petList.toString());

        pet_listview = (ListView) findViewById(R.id.pet_listview);
        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList);
        pet_listview.setAdapter(adapter);

        context = this;

        pet_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.w("MA", Integer.toString(i));

                pet = petList.get(i);
                Log.w("MA", "pet data");
                Log.w("MA", pet.toString());

                //TODO code below causes a crash when launching pet activity
                Intent intent = new Intent(context, Pet.class);
                Log.w("MA", "intent created");
                intent.putExtra(pet.name, "petToView.name");
                intent.putExtra(pet.animal, "petToView.animal");
                Log.w("MA", "successful puts");
                //add more data here
                startActivity(intent);
                Log.w("MA", "activity started");
                finish();

            }
        });
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
