package com.example.eventish;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

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
import java.nio.channels.AsynchronousChannelGroup;

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
            String s = readStream(in);

            // to remove jsonFlickrFeed in front of response
//            String jsonExtract = s.substring("jsonFlickrFeed(".length(), s.length() - 1);
            Log.i("JFL", s);

            jsonObject = new JSONObject(s);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

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
/*
{
  "_embedded": {
    "events": [
      {
        "name": "Hamilton",
        "type": "event",
        "images": [
          {
            "ratio": "16_9",
            "url": "https://s1.ticketm.net/dam/a/dd1/9273b646-953f-467e-8e79-ec6eece1edd1_1267151_RECOMENDATION_16_9.jpg",
            "width": 100,
 */
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {
            JSONObject urls = jsonObject.getJSONObject("_embedded");
            JSONArray eventsArray = urls.getJSONArray("events");

//            get all the image urls
            for(int i=0;i<eventsArray.length();i++){

                JSONObject event = eventsArray.getJSONObject(i);

                String eventName = event.getString("name");
                String eventDate = event.getJSONObject("dates").getJSONObject("start").getString("localDate");
                String eventImage = event.getJSONArray("images").getJSONObject(0).getString("url");
                Event event1 = new Event();

                event1.setName(eventName);
                event1.setDate(eventDate);
                event1.setDescription("fyvgebinjxwqmk");
                event1.setImage(eventImage);
                event1.setPrice(99);
                event1.setTime("noon");

                adp.add(event1); // add them to the adapter
                Log.i("IMG", "Added to adapter img: "+eventImage);
                adp.notifyDataSetChanged(); // notify the adapter when a new image url is added
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
