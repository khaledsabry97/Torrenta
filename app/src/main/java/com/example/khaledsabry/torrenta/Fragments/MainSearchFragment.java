package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Enums.Type;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.jaredrummler.materialspinner.MaterialSpinner;


public class MainSearchFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;
    SearchView searchView;
    TorrentFragment torrentFragment;
    MaterialSpinner provider;
    MaterialSpinner quality;
    MaterialSpinner resolution;
    MaterialSpinner codec;
    MaterialSpinner features;
    View movieNavigationInclude;
    View tvNavigationInclude;
    public static Type type;


    public static MainSearchFragment newInstance(Type type) {
        MainSearchFragment fragment = new MainSearchFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_search, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        drawerLayout = view.findViewById(R.id.drawer_layout_id);
        navigationView = view.findViewById(R.id.navigation_view_id);
        resolution = view.findViewById(R.id.resolution_spinner_id);
        provider = view.findViewById(R.id.provider_spinner_id);
        quality = view.findViewById(R.id.quality_spinner_id);
        codec = view.findViewById(R.id.codec_spinner_id);
        features = view.findViewById(R.id.features_spinner_id);
        movieNavigationInclude = view.findViewById(R.id.movie_id);
        tvNavigationInclude = view.findViewById(R.id.tv_id);


        torrentFragment = TorrentFragment.newInstance();

        setupToolbar();
        determineType();

        MainActivity.loadFragmentNoReturn(R.id.torrent_search_items_id, torrentFragment);


        return view;
    }

    private void setupToolbar() {
        MainActivity.getActivity().setSupportActionBar(toolbar);

    }


    private void determineType() {
        switch (type) {
            case general:
                adjustLayoutForAll();
                break;
            case movie:
                adjustLayoutForMovie();
                break;
            case tv:
                adjustLayoutForTv();
                break;
            case software:
                adjustLayoutForSoftware();
                break;
            case games:
                adjustLayoutForGames();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_bar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                torrentFragment.search(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


    private void adjustLayoutForAll() {
        toolbar.setTitle("All Torrents");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void adjustLayoutForMovie() {
        toolbar.setTitle("Movies Torrents");
        movieNavigationInclude.setVisibility(View.VISIBLE);
    }

    private void adjustLayoutForTv() {
        toolbar.setTitle("Tv Series Torrents");
        tvNavigationInclude.setVisibility(View.VISIBLE);
    }


    private void adjustLayoutForGames() {
        toolbar.setTitle("Games Torrents");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void adjustLayoutForSoftware() {
        toolbar.setTitle("Software Torrents");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

}
