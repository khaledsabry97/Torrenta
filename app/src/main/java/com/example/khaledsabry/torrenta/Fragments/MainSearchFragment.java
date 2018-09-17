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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;


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
    MaterialSpinner sort;
    MaterialSpinner season;
    MaterialSpinner episode;
    View movieNavigationInclude;
    View tvNavigationInclude;
    public static Type type;
    String categorySearch = "";
    String containerSearchString = "";
    String searchText = "";

    View movieContainer;
    View tvContainer;


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
        movieContainer = view.findViewById(R.id.movie_id);
        tvContainer = view.findViewById(R.id.tv_id);

        MobileAds.initialize(getContext(), "ca-app-pub-9364776584437772~6544419095");
        AdView mAdView;
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
                searchText = newText;
                search();
                //
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


    private void adjustLayoutForAll() {
        toolbar.setTitle("General");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        categorySearch = "";
    }

    private void adjustLayoutForMovie() {
        toolbar.setTitle("Movies");
        movieNavigationInclude.setVisibility(View.VISIBLE);
        categorySearch = "&category=movie";
        setupMovieContainer();
    }


    private void adjustLayoutForTv() {
        toolbar.setTitle("Tv Series");
        tvNavigationInclude.setVisibility(View.VISIBLE);
        categorySearch = "&category=show";
        setupTvContainer();

    }


    private void adjustLayoutForGames() {
        toolbar.setTitle("Games");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        categorySearch = "&type=games";

    }

    private void adjustLayoutForSoftware() {
        toolbar.setTitle("Software Torrents");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        categorySearch = "&type=software";

    }


    private void setupMovieContainer() {
        disableAllContainers();
        resetSpinners();
        movieContainer.setVisibility(View.VISIBLE);
        sort = movieContainer.findViewById(R.id.sort_spinner_id);
        resolution = movieContainer.findViewById(R.id.resolution_spinner_id);
        provider = movieContainer.findViewById(R.id.provider_spinner_id);
        quality = movieContainer.findViewById(R.id.quality_spinner_id);
        codec = movieContainer.findViewById(R.id.codec_spinner_id);
        features = movieContainer.findViewById(R.id.features_spinner_id);
        setCodec();
        setFeatures();
        setProvider();
        setQuality();
        setResolution();
        setSort();
    }

    private void setupTvContainer() {
        disableAllContainers();
        resetSpinners();
        tvContainer.setVisibility(View.VISIBLE);
        sort = tvContainer.findViewById(R.id.sort_spinner_id);
        resolution = tvContainer.findViewById(R.id.resolution_spinner_id);
        provider = tvContainer.findViewById(R.id.provider_spinner_id);
        quality = tvContainer.findViewById(R.id.quality_spinner_id);
        codec = tvContainer.findViewById(R.id.codec_spinner_id);
        features = tvContainer.findViewById(R.id.features_spinner_id);
        episode = tvContainer.findViewById(R.id.episode_spinner_id);
        season = tvContainer.findViewById(R.id.seasons_spinner_id);
        setCodec();
        setFeatures();
        setProvider();
        setQuality();
        setResolution();
        setSort();
        setSeason();
        setEpisode();
    }

    private void disableAllContainers() {
        movieContainer.setVisibility(View.GONE);
        tvContainer.setVisibility(View.GONE);
    }

    private void resetSpinners() {
        sort = null;
        provider = null;
        codec = null;
        features = null;
        quality = null;
        resolution = null;
        season= null;
        episode = null;
    }

    /**
     * set the different types of sort
     */
    private void setSort() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("Relevance");
        adapter.add("Seeders");
        adapter.add("Leechers");
        adapter.add("Date");


        sort.setItems(adapter);
        sort.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                search();
            }
        });
    }

    /**
     * set the different types of codecs
     */
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

    /**
     * set the different types of providers
     */
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

    /**
     * set the different types of qualities
     */
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

    /**
     * set the differnet types of resolutions
     */
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

    /**
     * set the different types of features
     */
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

    /**
     * set the different types of seasons
     */
    private void setSeason() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        for(int i=1;i<30;i++)
        {
            String s="S";
            if (i<10)
                s+="0";
            s+=String.valueOf(i);
            adapter.add(s);
        }


        season.setItems(adapter);
        season.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    /**
     * set the different types of episodes
     */
    private void setEpisode() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        for(int i=1;i<30;i++)
        {
            String s="E";
            if (i<10)
                s+="0";
            s+=String.valueOf(i);
            adapter.add(s);
        }


        episode.setItems(adapter);
        episode.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    /**
     * the function that is responsible for the search to torrents
     */
    private void search() {
        String mprovider = "";
        String mquality = "";
        String mresolution = "";
        String mcodec = "";
        String mfeatures = "";
        String mSort = "";
        String mSeason ="";
        String mEpisode ="";

        if (sort != null)
            if (sort.getItems().get(sort.getSelectedIndex()).toString().equals("Relevance"))
                mSort = "";
            else {
                mSort = "&sort=";
                if (sort.getItems().get(sort.getSelectedIndex()).toString().equals("Date"))
                    mSort += "created";
                else
                    mSort += sort.getItems().get(sort.getSelectedIndex()).toString();
            }
        if (features != null)
            if (features.getItems().get(features.getSelectedIndex()).toString().equals("SoundTrack"))
                mfeatures = " " + features.getItems().get(features.getSelectedIndex()).toString();

        if (resolution != null)
            if (!resolution.getItems().get(resolution.getSelectedIndex()).toString().equals("All"))
                mresolution = " " + resolution.getItems().get(resolution.getSelectedIndex()).toString();

        if (quality != null)
            if (!quality.getItems().get(quality.getSelectedIndex()).toString().equals("All"))
                mquality = " " + quality.getItems().get(quality.getSelectedIndex()).toString();

        if (codec != null)
            if (!codec.getItems().get(codec.getSelectedIndex()).toString().equals("All"))
                mcodec = " " + codec.getItems().get(codec.getSelectedIndex()).toString();
        if (provider != null)
            if (!provider.getItems().get(provider.getSelectedIndex()).toString().equals("All"))
                mprovider = " " + provider.getItems().get(provider.getSelectedIndex()).toString();

        if (season != null)
            if (!season.getItems().get(season.getSelectedIndex()).toString().equals("All"))
                mSeason = season.getItems().get(season.getSelectedIndex()).toString();

        if (episode != null)
            if (!episode.getItems().get(episode.getSelectedIndex()).toString().equals("All"))
                mEpisode = episode.getItems().get(episode.getSelectedIndex()).toString();


        String searchString = searchView.getQuery().toString() + mresolution + mquality + mcodec + mprovider + mfeatures;
        torrentFragment.search(searchString+" "+mSeason+mEpisode + categorySearch + mSort);
    }


    /**
     * reset resolution,quality,provider,codec and features
     */
    private void resetResolution() {
        resolution.setSelectedIndex(0);
    }

    private void resetQuality() {
        quality.setSelectedIndex(0);
    }

    private void resetProvider() {
        provider.setSelectedIndex(0);
    }

    private void resetCodec() {
        codec.setSelectedIndex(0);
    }

    private void resetFeatures() {
        features.setSelectedIndex(0);
    }
}
