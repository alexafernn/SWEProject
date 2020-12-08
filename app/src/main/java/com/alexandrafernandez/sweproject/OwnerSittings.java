package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OwnerSittings extends AppCompatActivity
{
    /**
     * List managing the sittings the owner has
     */
    ArrayList<OwnerSittingData> ownerSittingList;

    /**
     * List View for managing multiple instance of an ownerSitting
     */
    ListView ownerSitting_listview;

    /**
     *  Owner Sitting information to be selected and accessed after request
     */
    OwnerSittingData ownerSitting;

    /**
     * Activity and View data
     */
    private Context context;

    /**
     * Text Views for identifying field components
     */
    TextView textViewCurrentSittings, textViewNoCurrentSittings;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientId, clientAuth;

    /**
     * On Create Method
     * Initializes the ownerSittings View and instantiates other view objects for later use
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner);
        setTitle("OWNER SITTINGS");

        //TODO get request, url connection, save response



        ownerSitting_listview = (ListView) findViewById(R.id.owner_listview);
        ownerSitting_listview.setVisibility(View.INVISIBLE); /** FOR NOW, But after it will check if they are sittigns and base visibility on that **/

        /** Currently commented out so it can work with not server connection  **/
//        ArrayAdapter<OwnerSittingData> adapter = new ArrayAdapter<OwnerSittingData>(this, android.R.layout.simple_expandable_list_item_1,ownerSittingList)
//        {
//        @Override
//           public View getView(int position, View convertView, ViewGroup parent)
//            {
//                TextView textView = (TextView) super.getView(position, convertView, parent);
//                textView.setTextColor(Color.WHITE);
//                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);
//                return textView;
//
//            }
//        };
//        ownerSitting_listview.setAdapter(adapter);

        context=this;


        /** Currently commented out so it can work with not server connection  **/
//        ownerSitting_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //ownerSitting = ownerSittingList.get(position);
//
//                Intent intent = new Intent(context, OwnerSitting.class);
//                //intent.putExtra("owner_sitting_id", ownerSitting.sittingID);
//                startActivity(intent);
//                finish();
//            }
//        });

        ScreenSize view = new ScreenSize(this);

        textViewCurrentSittings = (TextView) findViewById(R.id.sitter_request_label);
        textViewCurrentSittings.setTextSize(view.getLabelTextSize());

        textViewNoCurrentSittings = (TextView) findViewById(R.id.noSittings);
        textViewNoCurrentSittings.setTextSize(view.getLabelTextSize());

        //TODO if there are no current sittines show textViewNoCurrentSittings and make the list view invisible
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
                //startActivity(new Intent(this, HomeActivity.class));
                startActivity(new Intent(this, sittingsForMyPet.class));
                return true;
            case R.id.my_pets_menu:
                startActivity(new Intent(this, Pets.class));
                return true;
            case R.id.subscription_menu:
                startActivity(new Intent(this, Subscription.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Request Sitter Event method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void onRequestSitterEvent(View view) {
        startActivity(new Intent(this, OwnerSitting.class));
        finish();
    }
}

