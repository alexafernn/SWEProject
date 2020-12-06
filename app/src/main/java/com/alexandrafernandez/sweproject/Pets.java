package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

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
        setContentView(R.layout.pets);
        setTitle("My Pets");

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
        boolean success = false;

        //Save response
        String json = pref.getString("pets.list", "");
        String id="", petName="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                id = keys.next();
                petName = jsonObject.getString(id);
                if(!id.equals("success"))
                    petList.add(new PetData(petName, id));
                else success = true;
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        if(!success) {
            Toast.makeText(this, "No pets found.", Toast.LENGTH_SHORT).show();
        }

        pet_listview = (ListView) findViewById(R.id.pet_listview);
        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                return textView;

            }
        };
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
                startActivity(new Intent(this, Pet.class)); //change back to AddPet if needed
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
