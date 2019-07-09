package com.example.utilitymanagement;

import android.content.Intent;
import android.os.Bundle;

import com.example.utilitymanagement.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinnerMachine;
    Spinner spinnerDate;
    final String[] currentTime = new String[3];
    final SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        spinnerMachine = (Spinner) findViewById(R.id.spinnerMachine);

        ArrayList<String> arrayListMachine = new ArrayList<>();
        arrayListMachine.add("ZT55");
        arrayListMachine.add("MIURA 1");
        arrayListMachine.add("MIURA 2");
        arrayListMachine.add("MIURA 3");
        arrayListMachine.add("IVAR");

        ArrayAdapter arrayAdapterMachine = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListMachine);
        spinnerMachine.setAdapter(arrayAdapterMachine);

        spinnerDate = (Spinner) findViewById(R.id.spinnerDate);

        ArrayList<String> arrayListDate = new ArrayList<>();
        arrayListDate.add(simpleDateFormat_date.format(Calendar.getInstance().getTime()));
        ArrayAdapter arrayAdapterDate = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListDate);
        spinnerDate.setAdapter(arrayAdapterDate);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Toast.makeText(this, "Click login!!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_cloud) {
            Intent intent = new Intent(this, cloudData.class);
            Bundle bundle = new Bundle();
            bundle.putString("machine", spinnerMachine.getSelectedItem().toString());
            bundle.putString("dateOfCheck", spinnerDate.getSelectedItem().toString());

            intent.putExtra("Data", bundle);
            Toast.makeText(this, "Click cloud data!!!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "Click CloudDataWithTab!!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CloudDataWithTab.class));
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
