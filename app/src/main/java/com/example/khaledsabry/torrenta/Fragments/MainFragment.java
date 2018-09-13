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

import com.example.khaledsabry.torrenta.Functions.Functions;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;

import java.io.Serializable;


public class MainFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public int type = 0;
    //0 --> all
    //1 --> movie
    //2 --> tv
    //3 --> games
    //4 --> software
    int id = -1;
    Bundle savedInstanceState;

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
        this.savedInstanceState = savedInstanceState;

        setNavigationView();


        return view;
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                id = item.getItemId();

                selectIndex(id);

                Functions.closeDrawerLayout(drawerLayout);
                return true;
            }
        });

        if (savedInstanceState != null)
        {
            if (!savedInstanceState.getBoolean("opened"))
                selectIndex(id);
    }    else
            selectIndex(id);
    }

    void selectIndex(int id) {
        switch (id) {
            //todo all fragment
            case R.id.all_id:
                type = 0;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, MainSearchFragment.newInstance(MainSearchFragment.type.other));
                break;
            //todo Movie fragment
            case R.id.movie_id:
                type = 1;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;
            //todo TvSeries fragment
            case R.id.tv_id:
                type = 2;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;
            //todo Games fragment
            case R.id.games_id:
                type = 3;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;
            //todo Software fragment
            case R.id.software_id:
                type = 4;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;
            //todo DATABASE fragment
            case R.id.download_id:
                type = -1;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;
            //todo ABOUTME fragment
            case R.id.about_me_id:
                type = -1;
                MainActivity.loadFragmentNoReturn(R.id.main_frame, null);
                break;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("time_data", (Serializable) id);
        outState.putBoolean("opened", true);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            id = (int) savedInstanceState.get("time_data");
            // restore value of members from saved state
        }


    }
}
