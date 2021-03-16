package com.example.eventish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HomeAdapter adapter;
    AsyncForCategoriesList asyList;
    ListView list;

    //search
    EditText seachField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list =  (ListView) findViewById(R.id.list);
        adapter = new HomeAdapter(MainActivity.this);

        asyList = new AsyncForCategoriesList(adapter);
        asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?locale=FR&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
        list.setAdapter(adapter);

    }


    // serach
    public void search(View view) {
        seachField = (EditText) findViewById(R.id.searchedText);
        String searching = seachField.getText().toString();
        Toast.makeText(this,"you search for "+searching, Toast.LENGTH_LONG).show();
    }
}