package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Adapters.TorrentAdapter;
import com.example.khaledsabry.torrenta.Controllers.TorrentController;
import com.example.khaledsabry.torrenta.Inteface.OnWebSuccess;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;

import java.util.ArrayList;


/**
 * Created by KhALeD SaBrY on 12-Sep-18.
 */

public class TorrentFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;

    TorrentController torrentController;
    TorrentAdapter torrentAdapter;

    public static TorrentFragment newInstance() {
        TorrentFragment fragment = new TorrentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);

        torrentController = new TorrentController();
        setupRecyclerView();

        return view;
    }

    /**
     * setup the recycler view to show the torrents files
     */
    private void setupRecyclerView() {
        torrentAdapter = new TorrentAdapter();
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    public void search(String searchName) {

            torrentController.downloadSkyTorrent(searchName, new OnWebSuccess.OnTorrentSearch() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrents) {
                    torrentAdapter.setTorrents(torrents);

                }
            });


    }


}
