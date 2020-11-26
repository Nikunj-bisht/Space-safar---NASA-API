package com.animesafar.animecontent.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    Context context;
    String url = "https://api.nasa.gov/planetary/apod?api_key=CU3N1Rj2khuFAAVsVc4lniQMdi8l5oiObLt46aFv";
   public static Bitmap bitmap;
   private String date="random";
   private File location;
    public DashboardFragment(Context context){

        this.context = context;

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        Button button = root.findViewById(R.id.button2);
        ImageView imageView = root.findViewById(R.id.imageView);
Button downl = root.findViewById(R.id.download);
        Button set = root.findViewById(R.id.button5);

        Animerequest.picofdayrequest(context,url,imageView,textView);



location = context.getExternalFilesDir("spaceimages");


set.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
});

downl.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        }

        else if(bitmap!=null){


            try {
                downloadimage(context,bitmap, Bitmap.CompressFormat.JPEG,"image/jpeg",date);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
else{
            Toast.makeText(context,"Sorry null",Toast.LENGTH_SHORT).show();

        }
    }

});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Select date");



                CalendarView calendarView = new CalendarView(context);
                  calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                      @Override
                      public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                          Toast.makeText(context, i+" "+i1+" "+i2, Toast.LENGTH_SHORT).show();

                          String i4 = String.valueOf(i2);
                          if(i2<10){
                              i4 = "0"+i4;
                          }
                          date = i+"-"+i1+"-"+i4;
url = "https://api.nasa.gov/planetary/apod?date="+date+"&api_key=CU3N1Rj2khuFAAVsVc4lniQMdi8l5oiObLt46aFv";
                      }
                  });
builder.setView(calendarView);
builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

Animerequest.picofdayrequest(context,url,imageView,textView);

    }
});

AlertDialog dialog = builder.create();
dialog.show();

            }
        });




        return root;
    }

    private void downloadimage(@NonNull Context context,@NonNull Bitmap bitmap,Bitmap.CompressFormat format,@NonNull String mimetype,String filename) throws IOException {


        try{

            File file = new File(location,"/"+date+".jpeg");

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
          //  outputStream.close();
        }



    }
}