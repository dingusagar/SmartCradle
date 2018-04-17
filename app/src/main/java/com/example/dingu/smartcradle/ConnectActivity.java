package com.example.dingu.smartcradle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ConnectActivity extends AppCompatActivity {
    Button connectButton;
    EditText ipText;
    RecyclerView scanListRecyclerView;
    ArrayList<Device> devicesInLan;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scanListRecyclerView = findViewById(R.id.scanListRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        scanListRecyclerView.setLayoutManager(mLayoutManager);
        devicesInLan = new ArrayList<>();
        scanListRecyclerView.setAdapter(new ScanListAdapter(devicesInLan));
        connectButton =findViewById(R.id.connectButton);
        ipText = findViewById(R.id.ipText);
        SharedPreferences sharedpreferences = getSharedPreferences(MyConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        ipText.setText(sharedpreferences.getString(MyConstants.CACHED_IP,""));

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipText.getText().toString();
                editor.putString(MyConstants.CACHED_IP,ipText.getText().toString());
                editor.commit();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ip));
                startActivity(i);
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }



}
