package com.example.eventish.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.example.eventish.Database.MyDatabase;
import com.example.eventish.MySingleton;
import com.example.eventish.R;
import com.example.eventish.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FavouriteAdapter extends BaseAdapter {

    Vector<Event> favs = new Vector<>();
    Context context;
    MyDatabase myDb;

    public FavouriteAdapter(Context context){
        this.context = context;
        this.myDb = new MyDatabase(context);
    }


    // get list of all events store in the db
    public List<Event> getAll(){
        Log.i("LIST", "entered get all");
        SQLiteDatabase db = myDb.getWritableDatabase(); // db helper method
        Cursor cursor = db.rawQuery("select * from " + myDb.DATABASE_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Event event = new Event();

                String name = cursor.getString(cursor.getColumnIndex(myDb.NAME));
                event.setName(name);

                String date = cursor.getString(cursor.getColumnIndex(myDb.DATE));
                event.setDate(date);

                String image = cursor.getString(cursor.getColumnIndex(myDb.IMAGE));
                event.setImage(image);

                String genre = cursor.getString(cursor.getColumnIndex(myDb.GENRE));
                event.setGerne(genre);

                String subgenre = cursor.getString(cursor.getColumnIndex(myDb.SUBGENRE));
                event.setSubgerne(subgenre);

                String venue = cursor.getString(cursor.getColumnIndex(myDb.VENUE));
                event.setVenue(venue);

                String city = cursor.getString(cursor.getColumnIndex(myDb.CITY));
                event.setCity(city);

                favs.add(event);
                notifyDataSetChanged();
                cursor.moveToNext();
            }
        }
        return favs;
    }

    @Override
    public int getCount() {
        return favs.size();
    }

    @Override
    public Object getItem(int position) {
        return favs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // declare a recycle view
        RecyclerView.ViewHolder holder;
//        getAll();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_card_events, null);
        }

        // get event position and cast to event
        Event i = (Event) getItem(position);
//        Event event = new Event();

        ImageView img = convertView.findViewById(R.id.cardViewImage);
        TextView title = convertView.findViewById(R.id.cardViewTitle);
        TextView date = convertView.findViewById(R.id.cardDate);
        TextView city = convertView.findViewById(R.id.eventCity);
        TextView venue = convertView.findViewById(R.id.eventVenue);
        TextView genre = convertView.findViewById(R.id.eventGenre);
        TextView subGenre = convertView.findViewById(R.id.eventSubGenre);

        ImageView favBtn = convertView.findViewById(R.id.addToFavListBtn);

        // disable button so you cannot add duplicates
        favBtn.setEnabled(false);

        title.setText(favs.get(position).getName());
        date.setText("Date: "+favs.get(position).getDate());
        city.setText(favs.get(position).getCity());
        venue.setText(favs.get(position).getVenue()+", ");
        genre.setText(favs.get(position).getGerne());
        subGenre.setText(favs.get(position).getSubgerne());

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
