package com.example.eventish;

import android.content.Context;
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
        TextView price = convertView.findViewById(R.id.cardPrice);

//        String eventTitle = event.getName();
//        String eventImage = event.getImage();
//
//        Log.i("IMG", eventImage);
//        Log.i("TITLE", eventTitle);

//        Event i = (Event) getItem(position);
//        String i = (String) getItem(position);
        Event i = (Event) getItem(position);

//        title.setText(urls.get(position).getName());
        title.setText(urls.get(position).getImage());
        date.setText(urls.get(position).getDate());
//        price.setText("$"+urls.get(position).getPrice());

        // Get a RequestQueue
        /*
        RequestQueue queue = MySingleton.getInstance(context).getRequestQueue();
        Response.Listener<Bitmap> rep_listener = response -> {
            img.setImageBitmap(response);
        };


        ImageRequest request = new ImageRequest(
                // i is the URL which is a string
                i, rep_listener, 50,
                70, ImageView.ScaleType.CENTER , Bitmap.Config.RGB_565, null);
        queue.add(request);
*/
        return convertView;
    }
}
