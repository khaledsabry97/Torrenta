package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Database.DatabaseTables;
import com.example.khaledsabry.torrenta.R;


public class HistoryViewFragment extends Fragment {



    public enum type
    {
        all,
        movie,
        tv,
        games,
        software
    }

    type type;
    HistoryController historyController;
    public static HistoryViewFragment newInstance(type type) {
        HistoryViewFragment fragment = new HistoryViewFragment();
fragment.type = type;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_view, container, false);

historyController = new HistoryController();
        select();
        return view;
    }

    private void select() {
        switch (type)
        {
            case all:
                getAll();
        }
    }


    public void getAll() {

    }

}
