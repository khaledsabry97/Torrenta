package com.example.khaledsabry.torrenta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.Torrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhALeD SaBrY on 15-Jul-18.
 */

public class TorrentAdapter extends RecyclerView.Adapter<TorrentAdapter.TorrentViewHolder> {
    ArrayList<Torrent> torrents = new ArrayList<>();

    public TorrentAdapter(ArrayList<Torrent> torrents) {
        this.torrents = torrents;
    }

    @NonNull
    @Override
    public TorrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_torrent, parent, false);

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
        return torrents.size();
    }

    class TorrentViewHolder extends RecyclerView.ViewHolder {
        Button downloadImage;
        TextView title;
        TextView seeders;
        TextView leechers;
        TextView size;
        TextView date;
        CardView cardView;

        public TorrentViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            seeders = itemView.findViewById(R.id.seeders);
            leechers = itemView.findViewById(R.id.leechers);
            downloadImage = itemView.findViewById(R.id.download_id);
            size = itemView.findViewById(R.id.size);
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
/*
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(magnet), "application/x-bittorrent");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    MainActivity.getActivity().startActivity(intent);*/

                    //     Intent intent = new Intent(Intent.ACTION_ALL_APPS);
                    //   intent.setData(Uri.parse(magnet));
                    //      MainActivity.getActivity().startActivity(intent);

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.addCategory(Intent.CATEGORY_DEFAULT);
                    i.setType("application/x-bittorrent");
                    i.setData(Uri.parse(torrent.getMagnet()));
                    Intent intent = generateTorrentIntent(MainActivity.getActivity().getApplicationContext(), i);

                    MainActivity.getActivity().startActivity(Intent.createChooser(intent, "send to"));

                }
            });
        }


        public Intent generateTorrentIntent(Context context, Intent intent) {
            final PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfo.size() > 0) {
                List<Intent> targetedShareIntents = new ArrayList<Intent>();
                for (ResolveInfo r : resolveInfo) {
                    Intent progIntent = (Intent) intent.clone();
                    String packageName = r.activityInfo.packageName;

                    progIntent.setPackage(packageName);
                    if (r.activityInfo.packageName.toLowerCase().contains("torrent"))
                        targetedShareIntents.add(progIntent);

                }
                if (targetedShareIntents.size() > 0) {
                    Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
                            "Select app to download");

                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                            targetedShareIntents.toArray(new Parcelable[]{}));

                    return chooserIntent;
                }
            }
            return null;
        }
    }
}
