package com.example.khaledsabry.torrenta.Controllers;

import android.os.AsyncTask;

import com.example.khaledsabry.torrenta.Interface.OnDatabaseSuccess;
import com.example.khaledsabry.torrenta.Interface.OnSuccess;
import com.example.khaledsabry.torrenta.MainActivity;
import com.example.khaledsabry.torrenta.items.HistoryItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryController extends Controller implements OnDatabaseSuccess.number {

private OnSuccess.bool listener = new OnSuccess.bool() {
    @Override
    public void onSuccess(boolean state) {

    }
};

    public void addAllToHistory(String name,String size, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name,size, all, this);


    }
    public void addMovieToHistory(String name,String size, final OnSuccess.bool listener)
    {
        this.listener = listener;

        databaseController.insertController().AddHistory(name,size, movie, this);


    }

    public void addTvToHistory(String name,String size, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name,size, tv, this);

    }

    public void addGamesToHistory(String name,String size, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name,size, games, this);


    }

    public void addSoftwareToHistory(String name,String size, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name,size, software, this);


    }


    public void getAllHistory(final OnSuccess.array listener)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
          ArrayList<JSONObject> jsonObjects =      databaseController.selectController().getAllHistory();
                final ArrayList<HistoryItem> historyItems =setJsonsToHistoryItem(jsonObjects);
                MainActivity.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //todo: is to put the data into the recycler view
                        listener.onSuccess((ArrayList<Object>) (Object) historyItems);
                    }
                });


            }
        });
    }



    private ArrayList<HistoryItem> setJsonsToHistoryItem(ArrayList<JSONObject> jsonObjects)
    {
        ArrayList<HistoryItem> historyItems = new ArrayList<>();

   for(JSONObject jsonObject:jsonObjects)
   {
       HistoryItem item = new HistoryItem();
       try {
           item.setName(jsonObject.getString(history.name));
           item.setDate(jsonObject.getString(history.date));
           item.setSize(jsonObject.getString(history.size));


           historyItems.add(item);


       } catch (JSONException e) {
           e.printStackTrace();
       }
   }

   return historyItems;


    }


    @Override
    public void onSuccess(int state) {
        if(state != -1)
            listener.onSuccess(true);
        else
            listener.onSuccess(false);
    }
}
