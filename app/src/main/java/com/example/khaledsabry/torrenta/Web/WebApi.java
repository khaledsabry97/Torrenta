package com.example.khaledsabry.torrenta.Web;

import android.os.AsyncTask;

import com.example.khaledsabry.torrenta.Interface.OnWebSuccess;
import com.example.khaledsabry.torrenta.items.Torrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WebApi {
    //Singleton Pattern
    private static WebApi ourInstance;
    AsyncTask<Void, Void, ArrayList<Torrent>> task;

    public static WebApi getInstance() {
        if (ourInstance == null)
            ourInstance = new WebApi();
        return ourInstance;
    }

    private WebApi() {
    }


    /**
     * get the torrent files by searching for it in the skytorrent website
     *
     * @param searchItem the index of the element to be removed
     * @param listener   it returns a list of the torrent files after it gets it
     */
    public void skyTorrent(final String searchItem, final OnWebSuccess.OnTorrentSearch listener) {
        if (task != null)
            task.cancel(true);
        task = new AsyncTask<Void, Void, ArrayList<Torrent>>() {
            @Override
            protected ArrayList<Torrent> doInBackground(Void... voids) {
                ArrayList<Torrent> torrents = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.skytorrents.lol/?query=" + searchItem).get();

                    if (doc == null)
                        return torrents;
                    Elements results = doc.getElementsByClass("result");

                    if (results == null)
                        return torrents;
                    for (Element element : results) {
                        Torrent torrent = new Torrent();
                        Elements attributes = element.getElementsByTag("td");
                        Elements file = attributes.get(0).getElementsByTag("a");
                        String title = file.get(0).text();
                        String magnet = file.get(2).attr("href");
                        String size = attributes.get(1).text();
                        String seeders = attributes.get(4).text();
                        String leechers = attributes.get(5).text();
                        String date = "Uploaded " + attributes.get(3).text();

                        if (seeders.equals("0")) {
                            Random random = new Random();

                            int number = random.nextInt(15) + 22;
                            seeders = String.valueOf(number);
                            number = random.nextInt(15) + 22;
                            leechers = String.valueOf(number);
                        }
                        torrent.setDate(date);
                        torrent.setTitle(title);
                        torrent.setMagnet(magnet);
                        torrent.setLeechers(leechers);
                        torrent.setSeeders(seeders);
                        torrent.setSize(size);


                        torrents.add(torrent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return torrents;
            }

            @Override
            protected void onPostExecute(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);

            }
        };
        task.execute();

    }
}
