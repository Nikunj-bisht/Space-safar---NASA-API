package com.animesafar.animecontent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;


import com.animesafar.animecontent.Datastorageclasses.Datastorage;
import com.animesafar.animecontent.Requestclasses.Animerequest;
import com.animesafar.animecontent.recyclerviewclasses.Recycleradaptorclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Showallimages extends AppCompatActivity implements Animerequest.Helperi,Recycleradaptorclass.movetodetailcallback{

    ArrayList<Datastorage> arrayList;
    RecyclerView recyclerView;
    JSONArray jsonArray;JSONObject jo;
    int morecontentcount = 15;
    LinearLayoutManager linearLayoutManager;
    boolean isscrolling = false;
    int current,total,scrolledout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showallimages);

        String p = getIntent().getStringExtra("prog");

        recyclerView = findViewById(R.id.recycle);
 linearLayoutManager= new LinearLayoutManager(this);

     String url = "https://images-api.nasa.gov/search?q="+p;
arrayList = new ArrayList<Datastorage>();


recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

           if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

               isscrolling = true;

           }

    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        current = linearLayoutManager.getChildCount();
        total = linearLayoutManager.getItemCount();
        scrolledout = linearLayoutManager.findFirstVisibleItemPosition();
        Toast.makeText(Showallimages.this, current+" "+scrolledout, Toast.LENGTH_SHORT).show();
            if(isscrolling && (current + scrolledout == total)){
findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                loadmorecontent();
                Toast.makeText(Showallimages.this, current+" "+scrolledout, Toast.LENGTH_SHORT).show();

            }

    }
});

        Animerequest.programdata(this,url,Showallimages.this);



    }

    private void loadmorecontent() {

        int extracount = morecontentcount+10;

        while(morecontentcount<=extracount){


            try{

                jsonArray = jo.getJSONObject("collection").getJSONArray("items");
                    String title = jsonArray.getJSONObject(morecontentcount).getJSONArray("data").getJSONObject(0).getString("title");

                    Log.d("value",jsonArray.getJSONObject(morecontentcount).getJSONArray("data").toString());
                    try{

                        String imageurl=jsonArray.getJSONObject(morecontentcount).getJSONArray("links").getJSONObject(0).getString("href");
                        String des = jsonArray.getJSONObject(morecontentcount).getJSONArray("data").getJSONObject(0).getString("description");

                        Datastorage datastorage = new Datastorage(imageurl,des,title);

                        arrayList.add(datastorage);

                    }catch (Exception e){

                    }


        //   Log.d("url",imageurl);

            }catch (Exception e){
                e.printStackTrace();
                Log.d("url","errorororororo");

            }
  morecontentcount++;
        }
        Recycleradaptorclass recycleradaptorclass = (Recycleradaptorclass) recyclerView.getAdapter();
        recycleradaptorclass.setmoreitems(arrayList);

        recycleradaptorclass.notifyDataSetChanged();
        findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    public void display(){

        recyclerView.setAdapter(new Recycleradaptorclass(arrayList,this,this));
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void sendthejsonreference(JSONObject jsonObject){
jo = jsonObject;

        try{

            jsonArray = jsonObject.getJSONObject("collection").getJSONArray("items");


           for(int i=0;i<15;i++){

               String title = jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("title");

Log.d("value",jsonArray.getJSONObject(i).getJSONArray("data").toString());
try{

    String imageurl=jsonArray.getJSONObject(i).getJSONArray("links").getJSONObject(0).getString("href");
    String des = jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("description");

    Datastorage datastorage = new Datastorage(imageurl,des,title);

    arrayList.add(datastorage);

}catch (Exception e){



}

           }

this.display();




         //   Log.d("url",imageurl);

        }catch (Exception e){
            e.printStackTrace();
            Log.d("url","errorororororo");

        }


    }

    @Override
    public void movetodetail(Bitmap bitmap, String descr) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("image",bytes);
        intent.putExtra("des",descr);
        startActivity(intent);




    }
}