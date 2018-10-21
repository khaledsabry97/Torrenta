package com.example.khaledsabry.torrenta.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Fragments.SearchFragment;
import com.example.khaledsabry.torrenta.Intents;
import com.example.khaledsabry.torrenta.Interface.OnSuccess;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 12-Sep-18.
 */

public class TorrentAdapter extends RecyclerView.Adapter<TorrentAdapter.TorrentViewHolder> {
    ArrayList<Torrent> torrents = new ArrayList<>();
    HistoryController historyController = new HistoryController();


    public void setTorrents(ArrayList<Torrent> torrents) {
        this.torrents = torrents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TorrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_torrent, parent, false);

        return new TorrentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TorrentViewHolder holder, int position) {
        Torrent torrent = torrents.get(position);
        holder.updateUi(torrent);
        YoYo.with(Techniques.FadeIn).playOn(holder.itemView);
    }

    @Override
    public int getItemCount() {
        if (torrents == null)
            return 0;
        return torrents.size();
    }

    class TorrentViewHolder extends RecyclerView.ViewHolder implements OnSuccess.bool {
        ImageView downloadImage;
        TextView title;
        TextView seeders;
        TextView leechers;
        TextView size;
        TextView date;
        CardView cardView;

        String name, sizes, magnets;

        public TorrentViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            seeders = itemView.findViewById(R.id.seeders_id);
            leechers = itemView.findViewById(R.id.leechers_id);
            downloadImage = itemView.findViewById(R.id.history_id);
            size = itemView.findViewById(R.id.size_id);
            date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.cardview);

        }


        public void updateUi(final Torrent torrent) {
            title.setText(torrent.getTitle());
            seeders.setText(torrent.getSeeders());
            leechers.setText(torrent.getLeechers());
            date.setText(torrent.getDate());
            size.setText(torrent.getSize());
            downloadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String magnet = torrent.getMagnet();

                    //global variables to use it later for history
                    name = title.getText().toString();
                    sizes = size.getText().toString();
                    magnets = magnet;

                    Intents.getInstance().downloadMagnetLink(magnet);
                    addToHistory();

                }
            });
        }


        /**
         * add to history in your local database
         */
        void addToHistory() {
            if (SearchFragment.type == null)
                return;
            switch (SearchFragment.type) {
                case general:
                    historyController.addGeneralToHistory(name, magnets, sizes, this);
                    break;
                case movie:
                    historyController.addMovieToHistory(name, magnets, sizes, this);
                    break;
                case tv:
                    historyController.addTvToHistory(name, magnets, sizes, this);
                    break;
                case games:
                    historyController.addGamesToHistory(name, magnets, sizes, this);
                    break;
                case software:
                    historyController.addSoftwareToHistory(name, magnets, sizes, this);
                    break;
            }
        }

        @Override
        public void onSuccess(boolean state) {

        }
    }
}
