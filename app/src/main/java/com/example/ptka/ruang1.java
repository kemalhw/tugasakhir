package com.example.ptka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ruang1 extends AppCompatActivity {

    Button btnhistory, btnoff;
    TextView amon, suhu, humidity;
    String suhux, amonx, humx;
    SeekBar seekBar;
    DatabaseReference reff;
    Integer off = 0, on = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_ruang1);

        seekBar = findViewById(R.id.seekbar1);
        amon = (TextView) findViewById(R.id.amox);
        suhu = (TextView) findViewById(R.id.suhux);
        humidity = (TextView) findViewById(R.id.humx);
        btnhistory = (Button) findViewById(R.id.btnhis);
        btnoff = (Button) findViewById(R.id.btnpow);

        //Get Data
        reff = FirebaseDatabase.getInstance().getReference("device01");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            suhux = dataSnapshot.child("temperature").getValue().toString();
            amonx = dataSnapshot.child("ammonia").getValue().toString();
            humx = dataSnapshot.child("humidity").getValue().toString();

            suhu.setText(suhux);
            amon.setText(amonx);
            humidity.setText(humx);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            reff = FirebaseDatabase.getInstance().getReference("device01").child("power");
            reff.setValue(off);

            }
        });

        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ruang1.this, HistoryActivity.class);
                ruang1.this.startActivity(intent);
            }
        });


        //DIMMER

        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Write code to perform some action when progress is changed.
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is started.
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is stopped.
                    reff = FirebaseDatabase.getInstance().getReference("device01").child("dimmer");
                    Toast.makeText(ruang1.this, "Current value is " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                    int seek = seekBar.getProgress();
                    reff.setValue(seek);
                }
            });

        }
    }
}