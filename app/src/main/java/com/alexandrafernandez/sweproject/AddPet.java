package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatActivity;

public class AddPet extends AppCompatActivity {

    public static Pet p;
    public static Pets pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pets = new Pets();
        setContentView(R.layout.add_pet);
        setTitle("Add a New Pet");
    }

    public void goToPets(View view)
    {
        Boolean energeticBoolean;
        Boolean noisyBoolean;
        Boolean trainedBoolean;
        Boolean insideOnlyBoolean;

        //do something with entered info, then go back to pets menu
        EditText animalName = (EditText) findViewById(R.id.pet_name_field);
        EditText animalType = (EditText) findViewById(R.id.animal_field);
        EditText otherInfo = (EditText) findViewById(R.id.other_quality_field);
        Switch energetic = (Switch) findViewById(R.id.switch3);
        Switch noisy = (Switch) findViewById(R.id.switch4);
        Switch trained = (Switch) findViewById(R.id.switch5);
        Switch insideOnly = (Switch) findViewById(R.id.switch6);

        String strAnimalName = animalName.getText().toString();
        String strAnimalType = animalType.getText().toString();
        String strOtherInfo = otherInfo.getText().toString();

        if(energetic.isChecked())
            energeticBoolean= true;
        else
            energeticBoolean= false;


        if(noisy.isChecked())
            noisyBoolean= true;
        else
            noisyBoolean= false;

        if(trained.isChecked())
            trainedBoolean= true;
        else
            trainedBoolean= false;

        if(insideOnly.isChecked())
            insideOnlyBoolean= true;
        else
            insideOnlyBoolean= false;

        p = new Pet(strAnimalName, strAnimalType, energeticBoolean, noisyBoolean, trainedBoolean, insideOnlyBoolean, strOtherInfo);
        System.out.println("the pet u just made is " + p.toString());
        pets.addPettoList(p);



        //testing
        //System.out.println("the boolean for energetic is " + energeticBoolean);
        //System.out.println("animal name entered is " + strAnimalName);

        finish();
        //when you go back need to make the text with id no pets invisible
    }

    public void addPhoto(View view) {

    }
}
