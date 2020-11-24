package com.animesafar.animecontent.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.animesafar.animecontent.R;
import com.animesafar.animecontent.Requestclasses.Animerequest;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SearchView searchView;
Button button;
TextView textView;
Context context;
ArrayAdapter arrayAdapter;
ListView listView ;
jumptoanother jump;
public  interface jumptoanother{

    void anotheractivity(String prog);

}


public HomeFragment(Context context,jumptoanother jumptoanother){
    this.context = context;
    this.jump = jumptoanother;
}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        List<String> list = Storagedata.getmylistofprograms();

        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,list);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
listView = root.findViewById(R.id.listview);
listView.setAdapter(arrayAdapter);

        searchView = root.findViewById(R.id.searchView2);

searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String s) {

jump.anotheractivity(s);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if(list.contains(s)){

            listView.setVisibility(View.VISIBLE);
            arrayAdapter.getFilter().filter(s);

        }

        return false;
    }
});




        return root;
    }
}