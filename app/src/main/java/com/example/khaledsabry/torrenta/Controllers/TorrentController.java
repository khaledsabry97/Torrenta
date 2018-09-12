package com.example.khaledsabry.torrenta.Controllers;

import com.example.khaledsabry.torrenta.Interface.OnWebSuccess;
import com.example.khaledsabry.torrenta.Web.WebApi;
import com.example.khaledsabry.torrenta.items.Torrent;

import java.util.ArrayList;

public class TorrentController {
    private WebApi webApi = WebApi.getInstance();

    public void downloadSkyTorrent(final String searchItem, final OnWebSuccess.OnTorrentSearch listener) {
        webApi.skyTorrent(searchItem, new OnWebSuccess.OnTorrentSearch() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);
            }
        });

    }






}







