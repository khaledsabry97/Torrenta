package com.example.khaledsabry.torrenta.Controllers;

import android.os.AsyncTask;

import com.example.khaledsabry.torrenta.Functions.Functions;
import com.example.khaledsabry.torrenta.Functions.Toasts;
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

    /**
     * these functions to add to history in the local database according to its type
     * @param name of the torrent
     * @param size of the torrent
     * @param listener to call it back if the operation is succeed = true else = false
     */
    public void addGeneralToHistory(String name, String size, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name,size, general, this);


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

    /**/


    /**
     * these function is to get every type from the database
     * @param listener to get back list of torrents in an array
     */
    public void getGeneralHistory(final OnSuccess.array listener)
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


    public void getMovieHistory(final OnSuccess.array listener)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<JSONObject> jsonObjects = databaseController.selectController().getMovieHistory();
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


    public void getTvHistory(final OnSuccess.array listener)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<JSONObject> jsonObjects =      databaseController.selectController().getTvHistory();
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



    public void getSoftwareHistory(final OnSuccess.array listener)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<JSONObject> jsonObjects =      databaseController.selectController().getSoftwareHistory();
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



    public void getGamesHistory(final OnSuccess.array listener)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<JSONObject> jsonObjects =      databaseController.selectController().getGamesHistory();
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

/**/


    /**
     * converts the json object array that come from the database into an array of history items
     * @param jsonObjects the objects from the database
     * @return array list of history items
     */
    private ArrayList<HistoryItem> setJsonsToHistoryItem(ArrayList<JSONObject> jsonObjects)
    {
        ArrayList<HistoryItem> historyItems = new ArrayList<>();

   for(JSONObject jsonObject:jsonObjects)
   {
       HistoryItem item = new HistoryItem();
       try {
           item.setId(jsonObject.getString(Functions.removeQoutes(history.id)));
           item.setName(jsonObject.getString(Functions.removeQoutes(history.name)));
           item.setDate(jsonObject.getString(Functions.removeQoutes(history.date)));
           item.setSize(jsonObject.getString(Functions.removeQoutes(history.size)));


           historyItems.add(item);


       } catch (JSONException e) {
           e.printStackTrace();
       }
   }

   return historyItems;


    }


    /**
     * delete item from the history
     * @param id of the torrent history item
     */
    public void deleteHistoryItem(final String id)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                databaseController.deleteController().historyDeleteItem(id);
                MainActivity.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toasts.success("Deleted Successfully");
                    }
                });
            }
        });
    }


    @Override
    public void onSuccess(int state) {
        if(state != -1)
            listener.onSuccess(true);
        else
            listener.onSuccess(false);
    }
}
