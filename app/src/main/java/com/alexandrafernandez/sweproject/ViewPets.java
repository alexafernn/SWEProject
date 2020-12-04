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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * View Pets Class
 * This class allows a sitter to view a list of the owner's pets
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class ViewPets extends AppCompatActivity {

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
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth;

    /**
     * On Create Method
     * Initializes the pets View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pets);
        setTitle("Owner's Pets");

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/petlist?id=" + clientID + "&auth=" + clientAuth,"pets.list", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        petList = new ArrayList<PetData>();

        //Save response
        String json = pref.getString("pets.list", "");
        String id="", petName="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                id = keys.next();
                petName = jsonObject.getString(id);
                petList.add(new PetData(petName, id));
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        pet_listview = (ListView) findViewById(R.id.pet_listview2);
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

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
