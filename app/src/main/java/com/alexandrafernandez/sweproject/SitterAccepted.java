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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SitterAccepted extends AppCompatActivity
{
    /**
     * List managing the sittings the sitter has
     */
    ArrayList<SitterSittingData> sitterSittingList;

    /**
     * List View for managing multiple instance of an sitterSitting
     */
    ListView sitterSitting_listview;

    /**
     *  Sitter Sitting information to be selected and accessed after request
     */
    SitterSittingData sitterSitting;

    /** Activity and View data
     */
    private Context context;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth;


    /**
     * On Create Method
     * Initializes the ownerSittings View and instantiates other view objects for later use
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_sittings);
        setTitle("ACCEPTED SITTINGS - Sitter");

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/sitterjoblist?id=" + clientID + "&auth=" + clientAuth + "&is_canceled=" + false,"requests.list", this);
        userInfo.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sitterSittingList = new ArrayList<SitterSittingData>();

        //Save response
        String json = pref.getString("requests.list", "");

        String startDateTime = "", endDateTime = "", details ="", id="", ownerName ="";
        boolean success = false, is_at_owner = false;
        float lat=0, lon=0;

        JSONObject jobData;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                id = keys.next();
                if(!id.equals("success")) {
                    jobData = jsonObject.getJSONObject(id);
                    startDateTime = jobData.getString("start_datetime");
                    endDateTime = jobData.getString("end_datetime");
                    ownerName =jobData.getString("owner_name");
                    sitterSittingList.add(new SitterSittingData(id, startDateTime, endDateTime, ownerName)); //TODO finish the rest of this class
                }
                else success = true;
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        if(!success) {
            Toast.makeText(this, "No sittings found.", Toast.LENGTH_SHORT).show();
        }





        sitterSitting_listview = (ListView) findViewById(R.id.sitting_listview);
        //sitterSitting_listview.setVisibility(View.INVISIBLE); /** FOR NOW, But after it will check if they are sittigns and base visibility on that **/


        /** Currently commented out so it can work with not server connection  **/
        ArrayAdapter<SitterSittingData> adapter = new ArrayAdapter<SitterSittingData>(this, android.R.layout.simple_list_item_1,sitterSittingList)
        {
        @Override
           public View getView(int position, View convertView, ViewGroup parent)
            {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);
                return textView;
            }
        };
        sitterSitting_listview.setAdapter(adapter);

        context=this;

        /** Currently commented out so it can work with not server connection  **/
        sitterSitting_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sitterSitting = sitterSittingList.get(position);

                Intent intent = new Intent(context, ManageAcceptedSitting.class);
                intent.putExtra("job_id", sitterSitting.sittingID);
                startActivity(intent);
                finish();
            }
        });

    }


    /**
     * Request toSit Event method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void onRequestSitterEvent(View view) {
        startActivity(new Intent(this, SitterSitting.class));
        finish();
    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (sitter_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sitter_accepted_menu, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //textViewNoCurrentSittings.setTextSize(view.getLabelTextSize());
}
