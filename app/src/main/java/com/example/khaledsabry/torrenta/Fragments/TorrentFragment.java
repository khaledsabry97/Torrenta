package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.torrenta.Adapters.TorrentAdapter;
import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Controllers.TorrentController;
import com.example.khaledsabry.torrenta.Interface.OnSuccess;
import com.example.khaledsabry.torrenta.Interface.OnWebSuccess;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by KhALeD SaBrY on 12-Sep-18.
 */

public class TorrentFragment extends Fragment implements OnSuccess.bool {

    RecyclerView recyclerView;

    TorrentController torrentController;
    TorrentAdapter torrentAdapter;
    HistoryController historyController;


ArrayList<Torrent> torrents;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("time_data", (Serializable) torrents);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (savedInstanceState != null) {
            // restore value of members from saved state
            torrents = (ArrayList<Torrent>) savedInstanceState.getSerializable("time_data");
        }


    }
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
historyController = new HistoryController();
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
        torrentAdapter.setTorrents(torrents);

    }


    public void search(String searchName) {

            torrentController.downloadSkyTorrent(searchName, new OnWebSuccess.OnTorrentSearch() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrent) {

                    torrents = torrent;
                    torrentAdapter.setTorrents(torrent);

                }});

    }


    @Override
    public void onSuccess(boolean state) {

    }
}
