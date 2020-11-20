package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Sitter extends AppCompatActivity {
    ArrayList<Sitting> sittingList;
    ListView sittings_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter);
        setTitle("SITTER");

        final Sitting[] sitting = {MainActivity.sitting};
        final Sitting test = new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot ");


        if (sitting[0].getNumberOfSittings() == 0)
        {
            Toast.makeText(getApplicationContext(),"No reservations open for sitting",Toast.LENGTH_SHORT).show();
        }
        else
        {

            sittingList = new ArrayList<Sitting>();
           // sittingList.add(new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot "));
            sittingList.add(test);
            sittings_listView = (ListView) findViewById(R.id.sitting2_listview);
            ArrayAdapter<Sitting> adapter = new ArrayAdapter<Sitting>(this, android.R.layout.simple_list_item_1, sittingList);
           // CustomAdapter myAdapter = new CustomAdapter(getApplicationContext(), sittingList);
            sittings_listView.setAdapter(adapter);

            sittings_listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id)
                {
                    Sitting list_row = sittingList.get(position);
                    //if position = 0
                    Intent intent = new Intent(Sitter.this, approveSitting.class); //idk
                    intent.putExtra("Myobject", String.valueOf(test));
                    startActivity(intent);
                    //else if postion == 1

                }
            });
            // do something when u click on the sitting

        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sitter_menu, menu);
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
}

