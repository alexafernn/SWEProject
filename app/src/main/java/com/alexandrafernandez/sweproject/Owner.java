package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Owner extends AppCompatActivity {

    Button sitter_request_button;
    TextView requestLabel, noSittingsLabel;
    ListView owner_listview;

    ArrayList<NeedSitterEventData> requestList;
    NeedSitterEventData event;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner);
        setTitle("OWNER");

        ScreenSize view = new ScreenSize(this);

        sitter_request_button = findViewById(R.id.sitter_request_button);
        sitter_request_button.setTextSize(view.getButtonTextSize());

        requestLabel = (TextView) findViewById(R.id.sitter_request_label);
        requestLabel.setTextSize(view.getLabelTextSize());

        noSittingsLabel = (TextView) findViewById(R.id.noSittings);
        noSittingsLabel.setTextSize(view.getLabelTextSize());

        requestList = new ArrayList<NeedSitterEventData>(); //replace with server pull
        requestList.add(new NeedSitterEventData("01/01/2021","12:00", "02/02/2021", "1:00", true, false, ""));

        owner_listview = (ListView) findViewById(R.id.owner_listview);
        ArrayAdapter<NeedSitterEventData> adapter = new ArrayAdapter<NeedSitterEventData>(this, android.R.layout.simple_list_item_1, requestList);
        owner_listview.setAdapter(adapter);

        context = this;

        owner_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                event = requestList.get(i);

                Intent intent = new Intent(context, NeedSitterEvent.class);
                intent.putExtra("viewRequest.dateStart", event.start_date);
                intent.putExtra("viewRequest.dateEnd", event.end_date);
                intent.putExtra("viewRequest.notes", event.other_notes);
                startActivity(intent);
                finish();
            }
        });

        ViewGroup.LayoutParams params = owner_listview.getLayoutParams();
        params.height = (int) (requestList.size()*view.getLabelTextSize()*4.5);
        owner_listview.setLayoutParams(params);
        owner_listview.requestLayout();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_menu, menu);
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

    public void onRequestSitterEvent(View view) {
        startActivity(new Intent(this, NeedSitterEvent.class));
    }
}
