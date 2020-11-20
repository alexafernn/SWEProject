package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Pets extends AppCompatActivity {
    ArrayList<PetData> petList;
    TextView[] pets;
    ListView pet_listview;
    PetData pet;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);
        setTitle("My Pets");

        petList = new ArrayList<PetData>(); //replace with server pull
        petList.add(new PetData("test1", "animal1", "", true, false, true, false));
        petList.add(new PetData("test2", "animal2","", false, true, false, true));

        pet_listview = (ListView) findViewById(R.id.pet_listview);
        ArrayAdapter<PetData> adapter = new ArrayAdapter<PetData>(this, android.R.layout.simple_list_item_1, petList);
        pet_listview.setAdapter(adapter);

        context = this;

        pet_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pet = petList.get(i);

                Intent intent = new Intent(context, Pet.class);
                intent.putExtra("petToView.name", pet.name);
                intent.putExtra("petToView.animal", pet.animal);
                intent.putExtra("petToView.other_type", pet.other_type);
                intent.putExtra("petToView.energetic", pet.energetic);
                intent.putExtra("petToView.noisy", pet.noisy);
                intent.putExtra("petToView.trained", pet.trained);
                intent.putExtra("petToView.inside_only", pet.inside_only);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pets_menu, menu);
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
            case R.id.add_pet_menu:
                startActivity(new Intent(this, AddPet.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
