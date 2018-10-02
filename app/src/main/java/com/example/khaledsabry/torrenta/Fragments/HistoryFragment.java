package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Adapters.HistoryAdapter;
import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Interface.OnSuccess;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.HistoryItem;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    ArrayList<Fragment> fragments;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    HistoryController historyController;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        tabLayout = view.findViewById(R.id.tab_layout_id);
        recyclerView = view.findViewById(R.id.recycler_id);
        historyAdapter = new HistoryAdapter();
        historyController = new HistoryController();
        setupTabLayout();
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        selectTab(0);
    }


    private void selectTab(int index) {
        switch (index) {
            case 0:
                historyController.getGeneralHistory(new OnSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<Object> historyItems) {
                        historyAdapter.setItems((ArrayList<HistoryItem>) (Object) historyItems);
                    }
                });

                break;
            case 1:
                historyController.getMovieHistory(new OnSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<Object> historyItems) {
                        historyAdapter.setItems((ArrayList<HistoryItem>) (Object) historyItems);
                    }
                });
                break;
            case 2:
                historyController.getTvHistory(new OnSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<Object> historyItems) {
                        historyAdapter.setItems((ArrayList<HistoryItem>) (Object) historyItems);
                    }
                });
                break;
            case 3:
                historyController.getGamesHistory(new OnSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<Object> historyItems) {
                        historyAdapter.setItems((ArrayList<HistoryItem>) (Object) historyItems);
                    }
                });
                break;
            case 4:
                historyController.getSoftwareHistory(new OnSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<Object> historyItems) {
                        historyAdapter.setItems((ArrayList<HistoryItem>) (Object) historyItems);
                    }
                });
                break;
        }
    }


}
