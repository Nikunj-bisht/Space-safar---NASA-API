package com.animesafar.animecontent.recyclerviewclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.animesafar.animecontent.Datastorageclasses.Datastorage;
import com.animesafar.animecontent.R;

import java.util.ArrayList;

public class Recycleradaptorclass extends RecyclerView.Adapter<Singleview> {


    ArrayList<Datastorage> datastorages;
    Context context;
    movetodetailcallback move;

      public Recycleradaptorclass(ArrayList<Datastorage> list, Context context,movetodetailcallback movv){

          this.datastorages = list;
          this.context = context;
          this.move = movv;
      }


     public interface movetodetailcallback{

           void movetodetail(Bitmap bitmap , String descr);

      }

    @NonNull
    @Override
    public Singleview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customview,parent,false);

  Singleview singleview = new Singleview(view);


        return singleview;
    }

    @Override
    public void onBindViewHolder(@NonNull Singleview holder, int position) {

holder.textView.setText(datastorages.get(position).getTitl());
holder.getImageView().setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View view) {

       BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.getImageView().getDrawable();
       Bitmap bitmap = bitmapDrawable.getBitmap();

move.movetodetail(bitmap,datastorages.get(position).getDescrip());

    }
});


  Fetchimage fetchimage = new Fetchimage(holder.getImageView(),datastorages.get(position).getImageuri());
         fetchimage.start();


    }


    public void setmoreitems(ArrayList<Datastorage> arrayList){

           this.datastorages = arrayList;


    }

    @Override
    public int getItemCount() {
        return datastorages.size();
    }

       class Fetchimage extends Thread{
          ImageView imageView;
          String newurl;

          public Fetchimage(ImageView imageView , String imageurl){

              this.imageView = imageView;
              this.newurl = imageurl;

          }

          public void run(){


              RequestQueue requestQueue = Volley.newRequestQueue(context);
              ImageRequest imageRequest = new ImageRequest(this.newurl, new Response.Listener<Bitmap>() {
                  @Override
                  public void onResponse(Bitmap response) {

                      imageView.setImageBitmap(response);


                  }
              }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {

                      Toast.makeText(context,"Sorry cant fetch this image ",Toast.LENGTH_SHORT).show();


                  }
              });

              //Toast.makeText(context,"Sorry newtwork error ",Toast.LENGTH_SHORT).show();

              requestQueue.add(imageRequest);


          }


       }




}
