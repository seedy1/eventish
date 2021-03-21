package com.example.eventish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.eventish.Database.MyDatabase;
import com.example.eventish.adapters.HomeAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    HomeAdapter adapter;
    AsyncForCategoriesList asyList;
    ListView list;
    BottomNavigationView bottomNav;

    MyDatabase myDb;
//    myDb = new MyDatabase(context);


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

        tabMenuSwitcher();

        asyList = new AsyncForCategoriesList(adapter);
        asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?locale=FR&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
        list.setAdapter(adapter);

    }

    void tabMenuSwitcher(){
        // select other menus
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.infoNav:
                        startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.locationNav:
                        startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favoriteNav:
                        startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homeNav:
                        return true;
                }
                return false;
            }
        });

    }

    // search on click listener
    public void search(View view) {
        seachField = (EditText) findViewById(R.id.searchedText);
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        String searching = seachField.getText().toString();
        intent.putExtra("search", searching); // add keyword to use in search results activity
        startActivity(intent);
//        Toast.makeText(this,"you search for "+searching, Toast.LENGTH_LONG).show();
    }

}