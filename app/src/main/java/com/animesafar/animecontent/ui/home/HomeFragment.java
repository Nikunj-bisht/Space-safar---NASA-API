package com.animesafar.animecontent.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SearchView searchView;
Button button;
TextView textView;
Context context;
public HomeFragment(Context context){
    this.context = context;
}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        searchView = root.findViewById(R.id.searchView2);
        textView = root.findViewById(R.id.textView);
        button = root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Animerequest

            }
        });


        return root;
    }
}