package com.example.eventish;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.example.eventish.Database.MyDatabase;
import com.example.eventish.model.Event;

import java.util.Vector;

public class HomeAdapter extends BaseAdapter {

//    Vector<String> urls = new Vector<>();
    Vector<Event> urls = new Vector<>();
    Context context;
    MyDatabase myDb;

    public HomeAdapter(Context context){
        this.context = context;
        this.myDb = new MyDatabase(context);
    }

    // insert
    public void insertData(String name, String date, String image, String genre, String subgenre, String veune, String city){
        Log.i("STARTINSERT", "Entered insert method");
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(myDb.NAME, name);
        values.put(myDb.DATE, date);
        values.put(myDb.IMAGE, image);
        values.put(myDb.GENRE, genre);
        values.put(myDb.SUBGENRE, subgenre);
        values.put(myDb.VENUE, veune);
        values.put(myDb.CITY, city);

        db.insert(myDb.DATABASE_TABLE_NAME, null, values);
        Log.i("ENDINSERT", "Entered insert method");

    }

    public void add(Event url){
        urls.add(url);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RecyclerView.ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_card_events, null);
        }

        Event event = new Event();
        ImageView img = convertView.findViewById(R.id.cardViewImage);
        TextView title = convertView.findViewById(R.id.cardViewTitle);
        TextView date = convertView.findViewById(R.id.cardDate);
        TextView city = convertView.findViewById(R.id.eventCity);
        TextView venue = convertView.findViewById(R.id.eventVenue);
        TextView genre = convertView.findViewById(R.id.eventGenre);
        TextView subGenre = convertView.findViewById(R.id.eventSubGenre);

        ImageView favBtn = convertView.findViewById(R.id.addToFavListBtn);



//        Event i = (Event) getItem(position);
//        String i = (String) getItem(position);
        //        Item currentItem = (Item) getItem(position);
        Event i = (Event) getItem(position);

        title.setText(urls.get(position).getName());
        date.setText(urls.get(position).getDate());
        city.setText(urls.get(position).getCity());
        venue.setText(urls.get(position).getVenue());
        genre.setText(urls.get(position).getGerne());
        subGenre.setText(urls.get(position).getSubgerne());

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, urls.get(position).getName(), Toast.LENGTH_LONG).show();
                insertData(urls.get(position).getName(), urls.get(position).getDate(), urls.get(position).getImage(), urls.get(position).getGerne(), urls.get(position).getSubgerne(), urls.get(position).getVenue(), urls.get(position).getCity());
                Toast.makeText(context, urls.get(position).getName()+" added to favourites.", Toast.LENGTH_LONG).show();
            }
        });

        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(context).getRequestQueue();
        Response.Listener<Bitmap> rep_listener = response -> {
            img.setImageBitmap(response);
        };


        ImageRequest request = new ImageRequest(
                // i is the URL which is a string
                i.getImage(), rep_listener, 50,
                70, ImageView.ScaleType.CENTER , Bitmap.Config.RGB_565, null);
        queue.add(request);

        return convertView;
    }
}
