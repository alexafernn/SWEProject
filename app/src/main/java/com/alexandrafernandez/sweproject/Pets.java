package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Pets Class
 * This class allows an owner to view a list of their pets
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Pets extends AppCompatActivity {

    /**
     * List managing the pets an owner has
     */
    ArrayList<PetData> petList;

    /**
     * List View for managing multiple content instances of pets
     */
    ListView pet_listview;

    /**
     * Pet information to be selected and accessed after assignment
     */
    PetData pet;

    /**
     * Activity and View data
     */
    private Context context;

    /**
     * On Create Method
     * Initializes the pets View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);
        setTitle("My Pets");

        //GET Request - get id/auth
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String clientID = pref.getString("id", "");
        String clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/petlist?id" + clientID + "&auth=" + clientAuth,"pets.list", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //TODO update this when documentation correct
        /*
        //Save response
        String json = pref.getString("pets.list", "");
        Log.w("MA", "json: " + json);
        boolean is_owner=false, is_sitter=false;
        String first_name="", last_name="", email="", phone_number="", my_address="";
        try {
            //TODO json stuff
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }


         */
        petList = new ArrayList<PetData>();
        //replace with server pull

        pet_listview = (ListView) findViewById(R.id.pet_listview);
        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList);
        pet_listview.setAdapter(adapter);

        context = this;

        pet_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pet = petList.get(i);

                Intent intent = new Intent(context, Pet.class);
                intent.putExtra("pet_id", pet.id);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (pets_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pets_menu, menu);
        return true;
    }

    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
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
