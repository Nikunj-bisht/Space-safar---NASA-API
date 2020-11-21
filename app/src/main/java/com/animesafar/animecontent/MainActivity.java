package com.animesafar.animecontent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.animesafar.animecontent.ui.dashboard.DashboardFragment;
import com.animesafar.animecontent.ui.home.HomeFragment;
import com.animesafar.animecontent.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this.navigationItemSelectedListener);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        /*
        searchView = findViewById(R.id.search);
    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView textView = findViewById(R.id.textView);

                    String name = searchView.getQuery().toString();
                    textView.setText(name);

        }
    });

*/

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()){

                case R.id.navigation_home:
                    fragment = new HomeFragment(MainActivity.this);
                break;

                case R.id.navigation_notifications:
                    fragment = new NotificationsFragment();
                break;

                case R.id.navigation_dashboard:

                    fragment = new DashboardFragment(MainActivity.this);
                    break;
            }


            getSupportFragmentManager().beginTransaction().replace(R.id.containerframe,fragment).commit();


            return true;
        }
    };

}