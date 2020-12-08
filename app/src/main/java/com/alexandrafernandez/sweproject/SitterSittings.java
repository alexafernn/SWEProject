package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SitterSittings extends AppCompatActivity
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
     * Text Views for identifying field components
     */
    TextView textViewNoCurrentSittings;

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
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_sittings);
        setTitle("SITTER SITTINGS");

        //TODO get request, url connection, save response

        sitterSitting_listview = (ListView) findViewById(R.id.sitting_listview);
        sitterSitting_listview.setVisibility(View.INVISIBLE); /** FOR NOW, But after it will check if they are sittigns and base visibility on that **/


        /** Currently commented out so it can work with not server connection  **/
//        ArrayAdapter<SitterSittingData> adapter = new ArrayAdapter<SitterSittingData>(this, android.R.layout.simple_expandable_list_item_1,sitterSittingList)
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
//        sitterSitting_listview.setAdapter(adapter);

        context=this;

        /** Currently commented out so it can work with not server connection  **/
//        sitterSitting_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //ownerSitting = sitterSittingList.get(position);
//
//                Intent intent = new Intent(context, SitterSitting.class);
//                //intent.putExtra("sitter_sitting_id", sitterSitting.sittingID);
//                startActivity(intent);
//                finish();
//            }
//        });

        ScreenSize view = new ScreenSize(this);
        textViewNoCurrentSittings = (TextView) findViewById(R.id.noSittings);
        textViewNoCurrentSittings.setTextSize(view.getLabelTextSize());

        //TODO if there are no current sittines show textViewNoCurrentSittings and make the list view invisible
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
        inflater.inflate(R.menu.sitter_menu, menu);
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
            case R.id.rewards_menu:
                startActivity(new Intent(this, Rewards.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            case R.id.sitter_Availability:
                startActivity(new Intent(this, SitterAvailability.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //textViewNoCurrentSittings.setTextSize(view.getLabelTextSize());
}