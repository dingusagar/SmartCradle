package com.example.dingu.smartcradle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SensorTweakActivity extends AppCompatActivity {

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    ToggleButton cryButton,wetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_tweak);

        cryButton = findViewById(R.id.toggleButtonBabyCry);
        wetButton = findViewById(R.id.toggleButtonWetSensor);

        final CradleSensorData cradleSensorData = new CradleSensorData(false,false);


        cryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cradleSensorData.setBabyCrying(true);
                    writeToDB(cradleSensorData,cryButton);
                } else {
                    // The toggle is disabled
                    cradleSensorData.setBabyCrying(false);
                    writeToDB(cradleSensorData, cryButton);

                }
            }
        });

        wetButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cradleSensorData.setBedWet(true);
                    writeToDB(cradleSensorData, wetButton);
                } else {
                    // The toggle is disabled
                    cradleSensorData.setBedWet(false);
                    writeToDB(cradleSensorData, wetButton);

                }
            }
        });





    }

    private void writeToDB(CradleSensorData cradleSensorData, final ToggleButton button) {
        button.setClickable(false);
        dbRef.child("CradleSensorData").setValue((cradleSensorData)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"updated successful",Toast.LENGTH_SHORT);
                button.setClickable(true);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to connect",Toast.LENGTH_SHORT);
                button.setClickable(true);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sensor_tweak:
                return true;
            case R.id.monitoring:
                startActivity(new Intent(SensorTweakActivity.this, CradleMonitoring.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}