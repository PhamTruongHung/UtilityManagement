package com.example.utilitymanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class cloudData extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("Hourly check/2019-07-07/ZT55");

    HourlyCheckType hourlyCheckTypeTmp = new HourlyCheckType("10:00:00", "Hung");

    ListView hourlyCheckListView;
    ArrayList<String> hourlyCheckArray;
    ArrayAdapter arrayAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_data);

        hourlyCheckListView = (ListView) findViewById(R.id.hourlyCheckListView);
        hourlyCheckArray = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hourlyCheckArray);
        hourlyCheckListView.setAdapter(arrayAdapter);

        //databaseReference.push().setValue(hourlyCheckTypeTmp);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HourlyCheckType hourlyCheckType_2 = dataSnapshot.getValue(HourlyCheckType.class);
                hourlyCheckArray.add(hourlyCheckType_2.getTimeCheck() + " - " + hourlyCheckType_2.getPersonCheck());
                arrayAdapter.notifyDataSetChanged();
                Log.d("Check", hourlyCheckType_2.getTimeCheck());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
