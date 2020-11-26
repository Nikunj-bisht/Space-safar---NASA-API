package com.animesafar.animecontent.ui.notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.animesafar.animecontent.Datastorageclasses.Datastorage;
import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;
import com.animesafar.animecontent.recyclerviewclasses.Recycleradaptorclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements Animerequest.Helperi ,Recycleradaptorclass.movetodetailcallback{

    private NotificationsViewModel notificationsViewModel;

    ArrayList<Datastorage> arrayList ;
    RecyclerView recyclerView;
    Context context;
   boolean isscrolling = false;
   int current,total,scrolledout;
   int morecontent = 4;
    JSONArray jsonArray;

    public NotificationsFragment(Context context){

            this.context = context;

        }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=CU3N1Rj2khuFAAVsVc4lniQMdi8l5oiObLt46aFv";

        arrayList = new ArrayList<Datastorage>();

        recyclerView = root.findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

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

                  if(isscrolling && current+scrolledout ==total ){

                      loadmore();


                  }



            }
        });


     Animerequest.programdata(context,url,this);



        return root;

    }

    private void loadmore() {

int extra = morecontent+5;

        while(morecontent<=extra){

            try{
                String img = jsonArray.getJSONObject(morecontent).getString("img_src");
                String imm =  img.replace("http","https");

                String name = jsonArray.getJSONObject(morecontent).getJSONObject("rover").getString("name");
                String date =  jsonArray.getJSONObject(morecontent).getJSONObject("rover").getString("landing_date");

                Datastorage datastorage = new Datastorage(imm,name,date
                        ,"Active");
arrayList.add(datastorage);
            }
            catch (Exception e){
                e.printStackTrace();
            }
morecontent++;

        }

        Recycleradaptorclass recycleradaptorclass = (Recycleradaptorclass) recyclerView.getAdapter();
recycleradaptorclass.setmoreitems(arrayList);
recycleradaptorclass.notifyDataSetChanged();

    }

    @Override
    public void sendthejsonreference(JSONObject jsonObject) {

try{

     jsonArray = jsonObject.getJSONArray("photos");

       for(int i=0;i<4;i++){

           try{
               String img = jsonArray.getJSONObject(i).getString("img_src");
              String imm =  img.replace("http","https");
               Log.d("img",img);
               String name = jsonArray.getJSONObject(i).getJSONObject("rover").getString("name");
               String date =  jsonArray.getJSONObject(i).getJSONObject("rover").getString("landing_date");

               Datastorage datastorage = new Datastorage(imm,name,date
               ,"Active");
               arrayList.add(datastorage);

           }
catch (Exception e){
               e.printStackTrace();
}


       }

       this.displayinrecycle();


}catch (Exception e){
    e.printStackTrace();
}



    }

    private void displayinrecycle() {

            recyclerView.setAdapter(new Recycleradaptorclass(arrayList,context, this));

    }

    @Override
    public void movetodetail(Bitmap bitmap, String descr) {

        Toast.makeText(context, "Nothing more", Toast.LENGTH_SHORT).show();


    }
}