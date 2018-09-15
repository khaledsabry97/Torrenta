package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.R;
import com.jaredrummler.materialspinner.MaterialSpinner;


public class MovieSearchFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    MaterialSpinner provider;
    MaterialSpinner quality;
    MaterialSpinner resolution;
    MaterialSpinner codec;
    MaterialSpinner features;
    public static MovieSearchFragment newInstance() {
        MovieSearchFragment fragment = new MovieSearchFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_search, container, false);
        drawerLayout = view.findViewById(R.id.drawer_layout_id);
        navigationView = view.findViewById(R.id.navigation_view_id);

        resolution = view.findViewById(R.id.resolution_spinner_id);
        provider = view.findViewById(R.id.provider_spinner_id);
        quality = view.findViewById(R.id.quality_spinner_id);
        codec = view.findViewById(R.id.codec_spinner_id);
        features = view.findViewById(R.id.features_spinner_id);

        return view;
    }

}
