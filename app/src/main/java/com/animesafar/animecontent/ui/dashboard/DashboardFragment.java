package com.animesafar.animecontent.ui.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    Context context;
    String url = "https://api.nasa.gov/planetary/apod?api_key=CU3N1Rj2khuFAAVsVc4lniQMdi8l5oiObLt46aFv";

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
                          String date = i+"-"+i1+"-"+i4;
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

        Animerequest.picofdayrequest(context,url,imageView,textView);


        return root;
    }
}