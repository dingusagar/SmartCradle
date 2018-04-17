package com.example.dingu.smartcradle;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dingu on 17/4/18.
 */

public class SongSelectDialog {
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String  options[] = {"Stop Playing" , "twinkle.mp3","goodnight.mp3" , "johnsons.mp3"};
    Context context;

    DatabaseReference dbRefSong = FirebaseDatabase.getInstance().getReference().child("SongSelect");
    public SongSelectDialog(final Context context) {
        this.context = context;
        setUpDialog();

    }

    public void setUpDialog()
    {
        builder = new AlertDialog.Builder(context);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Select a Song");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.addAll(options);

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String songName = arrayAdapter.getItem(which);
                SongPlayer songPlayer = new SongPlayer();
                songPlayer.setSongName(options[1]);
                songPlayer.setPlaying(true);

                if(which == 0){ // Stop Playing
                    songPlayer.setPlaying(false);
                }else
                {
                    songPlayer.setSongName(songName);
                }
                dbRefSong.setValue(songPlayer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,songName,Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"Some Network Error...Try Again",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void showSongSelectionDialog()
    {
        setUpDialog();
        dialog = builder.create();
        dialog.show();
    }
}
