package com.animesafar.animecontent.Requestclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Animerequest {



public static void picofdayrequest(Context context, String url, ImageView imageView , TextView textView){

    RequestQueue requestQueue = Volley.newRequestQueue(context);

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {


            try {
                String explanation = response.getString("explanation");
                textView.setText(explanation);
                String imageurl = response.getString("url");
                Animerequest.imagerequest(context,imageView,imageurl);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });


    requestQueue.add(jsonObjectRequest);

}


private static void imagerequest(Context context,ImageView imageView,String urlname){

    RequestQueue requestQueue = Volley.newRequestQueue(context);
    ImageRequest imageRequest = new ImageRequest(urlname, new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {

            imageView.setImageBitmap(response);


        }
    }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });

    Toast.makeText(context,"Sorry newtwork error ",Toast.LENGTH_SHORT).show();

             requestQueue.add(imageRequest);

}


}
