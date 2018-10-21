package com.example.khaledsabry.torrenta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.torrenta.Controllers.HistoryController;
import com.example.khaledsabry.torrenta.Intents;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.R;
import com.example.khaledsabry.torrenta.items.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    //array of history items
    ArrayList<HistoryItem> items;


    public void setItems(ArrayList<HistoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_download_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        holder.updateUi(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView title, downloadedAt, size;
        ImageView redownload,share,remove;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
            downloadedAt = itemView.findViewById(R.id.date_id);
            size = itemView.findViewById(R.id.size_id);
            remove = itemView.findViewById(R.id.remove_id);
            share = itemView.findViewById(R.id.share_id);
            redownload = itemView.findViewById(R.id.redownload_id);
        }


        void updateUi(final HistoryItem historyItem) {
            title.setText(historyItem.getName());
            downloadedAt.setText("Downloaded at : " + historyItem.getDate());
            size.setText("Size: " + historyItem.getSize());
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(historyItem);

                }
            });


            redownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intents.getInstance().downloadMagnetLink(historyItem.getMagnet());
                }
            });


            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intents.getInstance().shareMagnetLink(historyItem.getMagnet());
                }
            });
        }

        void deleteItem(HistoryItem historyItem) {
            //after you delete it
            HistoryController historyController = new HistoryController();
            historyController.deleteHistoryItem(historyItem.getId());
            //remove from the list the item and update the list
            items.remove(historyItem);
            setItems(items);
        }




    }
}
