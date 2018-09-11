package com.example.khaledsabry.torrenta.Inteface;

import com.example.khaledsabry.torrenta.items.Torrent;

import java.util.ArrayList;

public interface OnWebSuccess {

     interface OnTorrentSearch
    {
        void onSuccess(ArrayList<Torrent> torrents);

    }



}
