package com.example.khaledsabry.torrenta.Database.DatabaseController;

import android.database.Cursor;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectController extends DatabaseController {
    private ArrayList<String> selects;
    private HashMap<String, String> tables;
    private String condition = "";

    public SelectController() {
        selects = new ArrayList<>();
        tables = new HashMap<String, String>();
    }


    /**
     * change the condition in each function and then call the general function
     *
     * @return array list of json object for history
     */
    public ArrayList<JSONObject> getHistory()
    {
        tables.put(history.tableName,null);

        String query = createSelectQuery(selects,tables,condition);
        Cursor cursor = getReadableDatabase().rawQuery(query,null);
        ArrayList<JSONObject> jsons = toJson(cursor);
        return jsons;
    }
    public ArrayList<JSONObject> getAllHistory()
    {
        condition += history.type + equal + history.constantAll;
      return   getHistory();
    }

    public ArrayList<JSONObject> getMovieHistory()
    {
        condition += history.type + equal + history.constantMovie;
        return   getHistory();
    }


    public ArrayList<JSONObject> getTvHistory()
    {
        condition += history.type + equal + history.constantTv;
        return   getHistory();
    }


    public ArrayList<JSONObject> getSoftwareHistory()
    {
        condition += history.type + equal + history.constantSoftware;
        return   getHistory();
    }

    public ArrayList<JSONObject> getGamesHistory()
    {
        condition += history.type + equal + history.constantGames;
        return   getHistory();
    }
}
