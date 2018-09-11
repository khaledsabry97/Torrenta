package com.example.khaledsabry.torrenta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.torrenta.Adapters.TorrentAdapter;
import com.example.khaledsabry.torrenta.Controllers.TorrentController;
import com.example.khaledsabry.torrenta.Inteface.OnWebSuccess;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TorrentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TorrentFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;
    // Spinner provider;
    // Spinner quality;
    // Spinner resolution;

    MaterialSpinner provider;
    MaterialSpinner quality;
    MaterialSpinner resolution;
    MaterialSpinner codec;
    MaterialSpinner features;
    SearchView customSearch;
    TorrentController torrentController;
    String search = "";

    public static TorrentFragment newInstance(String searchName) {
        TorrentFragment fragment = new TorrentFragment();
        TorrentFragment.searchName = searchName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);
       // resolution = view.findViewById(R.id.resolutionspinnerid);
    //    provider = view.findViewById(R.id.providerspinnerid);
    //    quality = view.findViewById(R.id.qualityspinnerid);
      //  customSearch = view.findViewById(R.id.customtextid);
     //   codec = view.findViewById(R.id.codecspinnerid);
      //  features = view.findViewById(R.id.featuresspinnerid);
        torrentController = new TorrentController();

        setResolution();
        setQuality();
        setProvider();
        setCodec();
        setCustomSearch();
        setFeatures();

        search();



        return view;
    }


    private void search() {
        String mprovider = "";
        String mquality = "";
        String mresolution = "";
        String mcodec = "";
        String mfeatures = "";

        if(features.getItems().get(features.getSelectedIndex()).toString().equals("SoundTrack"))
            mfeatures = " " + features.getItems().get(features.getSelectedIndex()).toString();

        if (!resolution.getItems().get(resolution.getSelectedIndex()).toString().equals("All"))
            mresolution = " " + resolution.getItems().get(resolution.getSelectedIndex()).toString();

        if (!quality.getItems().get(quality.getSelectedIndex()).toString().equals("All"))
            mquality = " " + quality.getItems().get(quality.getSelectedIndex()).toString();

        if (!codec.getItems().get(codec.getSelectedIndex()).toString().equals("All"))
            mcodec = " " + codec.getItems().get(codec.getSelectedIndex()).toString();

        if (!provider.getItems().get(provider.getSelectedIndex()).toString().equals("All"))
            mprovider = " " + provider.getItems().get(provider.getSelectedIndex()).toString();

        String searchString = searchName + mresolution + mquality + mcodec + mprovider+mfeatures;
        if (!search.equals(searchString)) {
            search = searchString;
            torrentController.downloadSkyTorrent(search, new OnWebSuccess.OnTorrentSearch() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrents) {

                    setObjects(torrents);

                }
            });
        }

    }




    private void setCodec() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("All");
        adapter.add("x264");
        adapter.add("x265");
        adapter.add("10bit x265");


        codec.setItems(adapter);
        codec.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    private void setProvider() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("PSA");
        adapter.add("YTS");
        adapter.add("MkvCage");
        adapter.add("SPARKS");
        adapter.add("RARBG");
        adapter.add("Ganool");
        adapter.add("MZABI");
        adapter.add("Joy");
        adapter.add("YIFY");


        provider.setItems(adapter);
        provider.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    private void setQuality() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("BluRay");
        adapter.add("BRRip");
        adapter.add("WEB-DL");
        adapter.add("WEBRip");
        adapter.add("HDRip");
        adapter.add("DVDSCR");
        adapter.add("HDTV");
        adapter.add("TS");
        adapter.add("CAM");



        quality.setItems(adapter);
        quality.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });

        quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().hideSystemUI();
            }
        });
    }

    private void setResolution() {

        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("480p");
        adapter.add("720p");
        adapter.add("1080p");
        adapter.add("2160p");
        resolution.setItems(adapter);
        resolution.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    private void setFeatures() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("None");
        adapter.add("SoundTrack");


        features.setItems(adapter);
        features.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetCodec();
                resetProvider();
                resetQuality();
                resetResolution();
                search();
            }
        });
    }


    private void setObjects(ArrayList<Torrent> torrents) {
        TorrentAdapter torrentAdapter = new TorrentAdapter(torrents);
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);


    }


    private void setCustomSearch()
    {
        customSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                torrentController.downloadSkyTorrent(query, new OnWebSuccess.OnTorrentSearch() {
                    @Override
                    public void onSuccess(ArrayList<Torrent> torrents) {
                        setObjects(torrents);
                    }
                });


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }




        });

    }



    private void resetResolution()
    {
        resolution.setSelectedIndex(0);
    }
    private void resetQuality()
    {
        quality.setSelectedIndex(0);
    }
    private void resetProvider()
    {
        provider.setSelectedIndex(0);
    }
    private void resetCodec()
    {
        codec.setSelectedIndex(0);
    }
    private void resetFeatures()
    {
        features.setSelectedIndex(0);
    }
}
