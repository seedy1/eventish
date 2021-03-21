package com.example.eventish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eventish.adapters.HomeAdapter;

public class SearchResultActivity extends AppCompatActivity {

    HomeAdapter adapter;
    AsyncForCategoriesList asyList;
    ListView list;
//    /discovery/v2/events
    //https://app.ticketmaster.com/discovery/v2/events.json?keyword=party&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

//        onOptionsItemSelected();

        Intent intent = getIntent();
        String s = intent.getStringExtra("search");

        list =  (ListView) findViewById(R.id.list);

        // empty list view
        TextView lv = (TextView) findViewById(R.id.emptyList);
        adapter = new HomeAdapter(SearchResultActivity.this);

        asyList = new AsyncForCategoriesList(adapter);
        asyList.execute("https://app.ticketmaster.com/discovery/v2/events.json?keyword="+s+"&apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W");
        list.setAdapter(adapter);
        list.setEmptyView(lv);
        lv.setText("Result Not Found!");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);

        // back button code
        switch(item.getItemId()){
            case R.id.homeNav:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
        }

        this.finish();
        return true;
    }


}