package com.animesafar.animecontent;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.animesafar.animecontent.Datastorageclasses.Coordinateclass;
import com.animesafar.animecontent.ui.Eventscate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        try {
            nowmarkallthemarkers();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void nowmarkallthemarkers() throws JSONException {

        for(String s : Coordinateclass.map.keySet()){

            JSONArray jsonArray = Coordinateclass.map.get(s);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
              JSONArray jsonArray1 =   jsonObject.getJSONArray("coordinates");
               String d =  jsonArray1.get(0).toString();
                String d2 =  jsonArray1.get(1).toString();

       LatLng sydney = new LatLng(Double.parseDouble(d),Double.parseDouble(d2));
        mMap.addMarker(new MarkerOptions().position(sydney).title(s));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            }


        }

    }
}