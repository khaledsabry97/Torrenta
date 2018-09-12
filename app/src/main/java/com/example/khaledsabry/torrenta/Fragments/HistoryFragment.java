package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Adapters.HistoryAdapter;
import com.example.khaledsabry.torrenta.R;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;


public class HistoryFragment extends Fragment {

    CoordinatorTabLayout tabLayout;
    ArrayList<String> headers;
    ArrayList<Fragment> fragments;
    ViewPager viewPager;
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
tabLayout = view.findViewById(R.id.coordinator_tab_layout_id);
viewPager = view.findViewById(R.id.view_pager_id);

setupFragments();
setupViewPager();
tabLayout.setTitle("History");
        return view;
    }

    private void setupFragments() {

    }

    private void setupViewPager() {
        viewPager.setAdapter(new HistoryAdapter(getFragmentManager(),headers,fragments));
    }

}
