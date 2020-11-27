package com.animesafar.animecontent.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.animesafar.animecontent.Datastorageclasses.Coordinateclass;
import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Eventscate extends Fragment implements Animerequest.Helperi , View.OnClickListener {

    //    HashMap<String,>
    JSONArray coor ;
    ImageView imageView;
    ImageView imageView1;
    Button button;
    View view;
gotomap go;

    public  interface gotomap{

        void mapact();

    }



    public Eventscate(gotomap g){
        this.go = g;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.eventscategories,container,false);


         imageView = view.findViewById(R.id.imageView5);
         imageView1 = view.findViewById(R.id.imageView6);
button = view.findViewById(R.id.button);
button.setOnClickListener(this);

        imageView.setX(2000);
        imageView1.setX(-2000);
        imageView.animate().translationXBy(-2000).setDuration(2000);

        imageView1.animate().translationXBy(2000).setDuration(2000);


        String url = "https://eonet.sci.gsfc.nasa.gov/api/v2.1/events";
        Animerequest.programdata(getContext(),url,this);

        return view;



    }

    @Override
    public void sendthejsonreference(JSONObject jsonObject) {

button.setVisibility(View.VISIBLE);
view.findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);
        try{


              JSONArray jsonArray = jsonObject.getJSONArray("events");

              for(int i=0;i<jsonArray.length()/2;i++){



                  String title = jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("title");

                  JSONArray jsonArray1= jsonArray.getJSONObject(i).getJSONArray("geometries");

                  if(!Coordinateclass.map.containsKey(title)){
                      Coordinateclass.map.put(title , new JSONArray());
                      Coordinateclass.map.put(title,jsonArray1);

                  }




              }


        }catch (Exception e){

            e.printStackTrace();

        }


    }

    @Override
    public void onClick( View view) {

//        Toast.makeText(getContext(),coor.toString(),Toast.LENGTH_LONG).show();

go.mapact();

    }
}
