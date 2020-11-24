package com.animesafar.animecontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.animesafar.animecontent.Requestclasses.Animerequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Showallimages extends AppCompatActivity implements Animerequest.Helperi{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showallimages);

        String p = getIntent().getStringExtra("prog");

     String url = "https://images-api.nasa.gov/search?q="+p;


        Animerequest.programdata(this,url,Showallimages.this);



    }

    public void display(){


    }


    @Override
    public void sendthejsonreference(JSONObject jsonObject){


        try{
            String imageurl = jsonObject.getJSONObject("collection").getJSONArray("items")
                    .getJSONObject(0).getJSONArray("links").getJSONObject(0).getString("href");

            Log.d("url",imageurl);

        }catch (Exception e){
            Log.d("url","errorororororo");

        }


    }
}