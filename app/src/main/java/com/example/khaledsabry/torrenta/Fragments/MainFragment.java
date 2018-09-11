package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Functions;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;


public class MainFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        drawerLayout = view.findViewById(R.id.drawer_layout_id);
        navigationView = view.findViewById(R.id.navigation_view_id);


        setNavigationView();


        return view;
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                switch (id) {
                    //todo all fragment
                    case 23:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo Movie fragment
                    case 22:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo TvSeries fragment
                    case 24:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo Games fragment
                    case 25:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo Software fragment
                    case 2634:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo DATABASE fragment
                    case 253:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;
                    //todo ABOUTME fragment
                    case 3426:
                        MainActivity.loadFragmentNoReturn(R.id.main_farme, null);
                        break;

                }
                Functions.closeDrawerLayout(drawerLayout);
                return true;
            }
        });
    }

}
