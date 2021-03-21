package com.example.eventish;

import android.content.Context;
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
import com.example.eventish.model.Event;

import java.util.Vector;

public class HomeAdapter extends BaseAdapter {

//    Vector<String> urls = new Vector<>();
    Vector<Event> urls = new Vector<>();
    Context context;

    public HomeAdapter(Context context){
        this.context = context;
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

//        title.setText(urls.get(position).getName());
        title.setText(urls.get(position).getName());
        date.setText(urls.get(position).getDate());
        city.setText(urls.get(position).getCity());
        venue.setText(urls.get(position).getVenue());
        genre.setText(urls.get(position).getGerne());
        subGenre.setText(urls.get(position).getSubgerne());

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, urls.get(position).getName(), Toast.LENGTH_LONG).show();
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
