package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khaledsabry.torrenta.Adapters.TorrentAdapter;
import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Controllers.TorrentController;
import com.example.khaledsabry.torrenta.Interface.OnSuccess;
import com.example.khaledsabry.torrenta.Interface.OnWebSuccess;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;
import com.github.ybq.android.spinkit.style.Wave;

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
    View searchState;
    TextView searchTextState;
    String currentSearchText="";

    ArrayList<Torrent> torrents;
ProgressBar progressBar;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("t", (Serializable) torrents);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            return;
        torrents = (ArrayList<Torrent>) savedInstanceState.getSerializable("t");
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
        searchState = view.findViewById(R.id.result_id);
        progressBar = view.findViewById(R.id.progress_bar_id);
        searchTextState = searchState.findViewById(R.id.result_text_id);
        historyController = new HistoryController();
        torrentController = new TorrentController();
        progressBar.setIndeterminateDrawable(new Wave());
        progressBar.setVisibility(View.GONE);
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
        if(torrents != null)
            searchFound();
        else
        noSearchText();

    }


    public void search(final String searchName) {
        if(searchName.length() == 0)
        {
            noSearchText();
            return;
        }
        currentSearchText = searchName;
     intermadiateSearch();

        torrentController.downloadSkyTorrent(searchName, new OnWebSuccess.OnTorrentSearch() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrent) {
                if(currentSearchText != searchName)
                    return;
                progressBar.setVisibility(View.GONE);
                torrents = torrent;
                torrentAdapter.setTorrents(torrent);

                if (torrent.size() == 0)
                    noSearchFound();
                else
                    searchFound();

            }
        });

    }


    @Override
    public void onSuccess(boolean state) {

    }

    /**
     * gone the layout of the recycler view
     * and show the layout for the no search found
     */
    private void noSearchFound() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        searchState.setVisibility(View.VISIBLE);
        searchTextState.setText(R.string.no_torrent_found);
    }

    private void searchFound() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        searchState.setVisibility(View.GONE);
    }

    private void noSearchText() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        searchState.setVisibility(View.VISIBLE);
        searchTextState.setText(R.string.write_something);
    }

    private void intermadiateSearch()
    {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        searchState.setVisibility(View.GONE);
    }
}
