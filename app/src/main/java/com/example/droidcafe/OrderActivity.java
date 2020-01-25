package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textView;
    Spinner spinner;
    int position=-1;
    RadioButton r1,r2,r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        r1 = (RadioButton)findViewById(R.id.sameDay);
        r2 = (RadioButton) findViewById(R.id.nextDay);
        r3 = (RadioButton) findViewById(R.id.pickUp);
        spinner = (Spinner) findViewById(R.id.spinner);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.labelArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null){
            spinner.setAdapter(adapter);
        }
        Intent intent = getIntent();
        String message = intent.getExtras().getString("order","");
        textView = (TextView) findViewById(R.id.recievedOrder);
        Log.d("message",message);
        textView.setText(message);

    }
    public void onRadioButtonClicked(View view) {
        int clicked = Integer.parseInt(view.getTag().toString());
        Log.d("value",Integer.toString(clicked));
        if(position==clicked){
            displayToast("Already Selected");
        }else{
            r1.setChecked(false);
            r2.setChecked(false);
            r3.setChecked(false);
            switch (view.getId()){
                case R.id.sameDay:
                    r1.setChecked(true);
                    displayToast(getString(R.string.same_day_messenger_service));
                    position=1;
                    break;
                case R.id.nextDay:
                    r2.setChecked(true);
                    displayToast(getString(R.string.next_day_ground_delivery));
                    position=2;
                    break;
                case R.id.pickUp:
                    r3.setChecked(true);
                    displayToast(getString(R.string.pick_up));
                    position=3;
                    break;

            }
        }
    }


    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String element = parent.getItemAtPosition(position).toString();
        displayToast(element);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
