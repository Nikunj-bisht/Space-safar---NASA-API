package com.animesafar.animecontent.recyclerviewclasses;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animesafar.animecontent.R;

public class Singleview extends RecyclerView.ViewHolder {


ImageView imageView;
TextView textView;

    public Singleview(@NonNull View itemView) {



         super(itemView);

         imageView = itemView.findViewById(R.id.imageView2);
         textView = itemView.findViewById(R.id.textView);


    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }
}
