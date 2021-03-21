package com.example.eventish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocationActivity extends AppCompatActivity {

//    https://app.ticketmaster.com//discovery/v2/suggest.json?latlong=48,4&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W

    HomeAdapter adapter;
    AsyncForCategoriesList asyList;
    ListView list;
    BottomNavigationView bottomNav;
    LocationManager locationManager;
    Double latitude;
    Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        list =  (ListView) findViewById(R.id.list);
        adapter = new HomeAdapter(LocationActivity.this);


        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.locationNav);

        // select other menus
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.locationNav:
                        return true;
                    case R.id.homeNav:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.infoNav:
                        startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


//        asyList = new AsyncForCategoriesList(adapter);
//        asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?countryCode=fr&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
//        list.setAdapter(adapter);

    }

    public void showEvents(View view) {

        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 48 );
        }

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

// Define a listener that responds to location updates
        if (locationManager != null) {
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
//                    Toast.makeText(getApplicationContext(), "Lat:"+latitude+" Log"+longitude, Toast.LENGTH_LONG).show();

                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            asyList = new AsyncForCategoriesList(adapter);
            asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?countryCode=fr&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
            list.setAdapter(adapter);
        }

    }
}