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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


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

    /**
     * Server interaction objects
     */
    String clientID, clientAuth, ownerId;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets_to_sit);
        setTitle("Pets in Reservation");

        ownerId = getIntent().getStringExtra("owner_id");

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/sitterpetlist?id=" + clientID + "&auth=" + clientAuth + "&owner_id=" + ownerId,"pet.sit.list", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        petList = new ArrayList<PetData>();
        boolean success = false;

        //Save response
        String json = pref.getString("pet.sit.list", "");
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
                Intent intent = new Intent(context, petBeingSat.class);
                intent.putExtra("pet_id", petData.id);
                startActivity(intent);

                finish();
            }
        });
    }
}
