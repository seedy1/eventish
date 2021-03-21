package com.example.eventish;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eventish.adapters.HomeAdapter;
import com.example.eventish.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncForCategoriesList extends AsyncTask<String, Void, JSONObject> {

    JSONObject jsonObject;
    HomeAdapter adp;
    //Example: https://app.ticketmaster.com/discovery/v2/events.json?apikey=BLBBzt3pKzrnEZWiHG0kgsVvKKwIjZ6W

    public AsyncForCategoriesList(HomeAdapter adp){
        this.adp = adp;
    }

    @Override
    protected JSONObject doInBackground(String... strings){

        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            // read response and return it as a JSON object
            String s = readStream(in);

//            Log.i("SJ", s);
            jsonObject = new JSONObject(s);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

//    method to read Input Stream
    private String readStream(InputStream in) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = in.read();
            while(i != -1) {
                bo.write(i);
                i = in.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {

            // start parsing the JSON Object returned
            JSONObject urls = jsonObject.getJSONObject("_embedded");
            JSONArray eventsArray = urls.getJSONArray("events");


            for(int i=0;i<eventsArray.length();i++){

                JSONObject event = eventsArray.getJSONObject(i);

                String eventName = event.getString("name");
                String externalLink = event.getString("url");

                String eventDate = event.getJSONObject("dates").getJSONObject("start").getString("localDate");
                String eventImage = event.getJSONArray("images").getJSONObject(4).getString("url");

                String eventGenre = event.getJSONArray("classifications").getJSONObject(0).getJSONObject("segment").getString("name");
                String eventSubgenre = event.getJSONArray("classifications").getJSONObject(0).getJSONObject("subGenre").getString("name");

                String venueName = event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getString("name");
                String venueCity = event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("city").getString("name");

                // add event details to Event object
                Event event1 = new Event();
                event1.setName(eventName);
                event1.setDate(eventDate);
                event1.setLink(externalLink);
                event1.setImage(eventImage);
                event1.setGerne(eventGenre);
                event1.setSubgerne(eventSubgenre);
                event1.setVenue(venueName);
                event1.setCity(venueCity);

                // add event object to adapter
                adp.add(event1); // add them to the adapter
                Log.i("IMG", "Added to adapter venue: "+venueName);
                adp.notifyDataSetChanged(); // notify the adapter when a new image url is added
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
