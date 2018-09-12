package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.github.rubensousa.floatingtoolbar.FloatingToolbarMenuBuilder;


public class MainSearchFragment extends Fragment {

    FloatingToolbar mFloatingToolbar;
    FloatingActionButton fab;

    Toolbar toolbar;
    SearchView searchView;
    TorrentFragment torrentFragment;

    static type type;
    public static MainSearchFragment newInstance(type type) {
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

        torrentFragment = TorrentFragment.newInstance();
       MainActivity.getActivity().setSupportActionBar(toolbar);

       determineType();
        MainActivity.loadFragmentNoReturn(R.id.torrent_search_items_id,torrentFragment);

        return view;
    }

    private void determineType() {
        switch (type)
        {
            case movie:
                //todo : load movie navigation fragment
                break;
            case tv:
                //todo: load tv navigation fragment
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

        inflater.inflate(R.menu.main_bar_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getContext(),newText,Toast.LENGTH_SHORT).show();
                torrentFragment.search(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


    public static enum type
    {
        movie,
        tv,
        other
    }
}
