package com.alexandrafernandez.sweproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ArrayList<Sitting> sittingList;
    ListView sittings_listView;
    Sitting sit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        setContentView(R.layout.current_sittings);
        setTitle("My Sittings");
        TextView noSittings = (TextView) findViewById(R.id.noSittings);

        final Sitting[] sitting = {MainActivity.sitting};

        System.out.println("number of sittings created is " + sitting[0].getNumberOfSittings());

        if (sitting[0].getNumberOfSittings() == 0)
        {
            noSittings.setVisibility(View.VISIBLE);
        }
        else
        {
            noSittings.setVisibility(View.INVISIBLE);
            sittingList = new ArrayList<Sitting>();
            sittingList.add(new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot "));
            sittings_listView = (ListView) findViewById(R.id.sitting_listview);
            ArrayAdapter<Sitting> adapter = new ArrayAdapter<Sitting>(this, android.R.layout.simple_list_item_1, sittingList);
            sittings_listView.setAdapter(adapter);

           // do something when u click on the sitting

         }
    }

    public Activity getActivity(){
        return this;
    }
}
