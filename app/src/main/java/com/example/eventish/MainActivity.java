package com.example.eventish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HomeAdapter adapter;
    AsyncForCategoriesList asyList;
    ListView list;
    BottomNavigationView bottomNav;

    //search
    EditText seachField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list =  (ListView) findViewById(R.id.list);
        adapter = new HomeAdapter(MainActivity.this);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.homeNav);

        // select other menus
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.infoNav:
                        startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homeNav:
                        return true;
                }
                return false;
            }
        });

        asyList = new AsyncForCategoriesList(adapter);
        asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?locale=FR&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
        list.setAdapter(adapter);

    }


    // serach
    public void search(View view) {
        seachField = (EditText) findViewById(R.id.searchedText);
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        String searching = seachField.getText().toString();
        intent.putExtra("search", searching);
        startActivity(intent);
//        Toast.makeText(this,"you search for "+searching, Toast.LENGTH_LONG).show();
    }
}