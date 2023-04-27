package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddLutemonActivity extends AppCompatActivity {

    private EditText lutemonNameInput;
    private String name;

    private Lutemon newLutemon;
    private Storage storage = Storage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);

        lutemonNameInput = findViewById(R.id.lutemonNameInput);
    }

    public void createLutemon(View view)    {
        name = String.valueOf(lutemonNameInput.getText());

        RadioGroup rgLutemonColor = findViewById(R.id.rgLutemonColor);
        switch (rgLutemonColor.getCheckedRadioButtonId())   {
            case R.id.rbWhite:
                newLutemon  = new White(name);
                break;
            case R.id.rbGreen:
                newLutemon  = new Green(name);
                break;
            case R.id.rbPink:
                newLutemon  = new Pink(name);
                break;
            case R.id.rbOrange:
                newLutemon  = new Orange(name);
                break;
            case R.id.rbBlack:
                newLutemon  = new Black(name);
                break;
        }

        lutemonNameInput.setText("");
        //System.out.println(newLutemon.name + " " + newLutemon.color + " " + newLutemon.maxHealth + " " + newLutemon.id); // test line
        storage.addLutemonToHome(newLutemon); //Add created Lutemon to lutemons at home
    }
    public void switchMainActivity(View view)    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}