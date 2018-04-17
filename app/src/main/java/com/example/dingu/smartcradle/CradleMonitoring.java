package com.example.dingu.smartcradle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CradleMonitoring extends AppCompatActivity {

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    TextView babyCryText, cradleWetText;
    CradleSensorData cradleSensorData;
    Button startLiveStreamButton,songSelectButton;
    MediaPlayer mediaPlayer;
    SongSelectDialog songSelectDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cradle_monitoring);

        songSelectDialog = new SongSelectDialog(this);
        mediaPlayer = new MediaPlayer();
        babyCryText = findViewById(R.id.baby_cry_status);
        cradleWetText = findViewById(R.id.wet_bed_status);
        startLiveStreamButton = findViewById(R.id.camera_connect);
        songSelectButton = findViewById(R.id.select_song_button);

        songSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSong();
            }
        });

        startLiveStreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = "http://10.42.0.131";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ip));
                startActivity(i);
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null)
                {
                    Log.d("TAG",dataSnapshot.toString());

                    cradleSensorData = dataSnapshot.child("CradleSensorData").getValue(CradleSensorData.class);
                    Log.d("TAG",cradleSensorData.toString());

                    AlertUser(cradleSensorData);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void selectSong() {
        songSelectDialog.showSongSelectionDialog();
    }

    private void AlertUser(CradleSensorData cradleSensorData) {
        int delay = 0;
        playAudioAlert(R.raw.alert,delay);
        delay = 1;
        if(cradleSensorData.getBabyCrying()){
//            baby is crying
            playAudioAlert(R.raw.baby_cry_alert,delay);
            delay = 2;
            babyCryText.setText(getResources().getText(R.string.baby_cry));
            babyCryText.setTextColor(getResources().getColor(R.color.negative));
        }else{
//            baby is not crying
            babyCryText.setText(getResources().getText(R.string.baby_no_cry));
            babyCryText.setTextColor(getResources().getColor(R.color.positive));
        }

        if(cradleSensorData.getBedWet()){
//            cradle is wet
            playAudioAlert(R.raw.bed_wet_alert,delay);
            cradleWetText.setText(getResources().getText(R.string.wet_bed));
            cradleWetText.setTextColor(getResources().getColor(R.color.negative));
        }else{
//            cradle is not wet
            cradleWetText.setText(getResources().getText(R.string.no_wet_bed));
            cradleWetText.setTextColor(getResources().getColor(R.color.positive));
        }
    }

    private void playAudioAlert(final int resourceID, int delay){

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run(){
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),resourceID);
                mediaPlayer.start();            }
        }, delay * 1000);

    }


}
