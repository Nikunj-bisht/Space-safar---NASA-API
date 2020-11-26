package com.animesafar.animecontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
   String description = getIntent().getStringExtra("des");

      Bitmap bitmap  = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);

     ImageView imageView =  findViewById(R.id.imageView3);
imageView.setImageBitmap(bitmap);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(description);



    }
}