package com.example.khaledsabry.torrenta.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HistoryAdapter extends FragmentPagerAdapter {
    ArrayList<String> titles;
    ArrayList<Fragment> fragments;

    public HistoryAdapter(FragmentManager fm, ArrayList<String> titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if(titles == null)
        return 0;
        return titles.size();
    }
}
