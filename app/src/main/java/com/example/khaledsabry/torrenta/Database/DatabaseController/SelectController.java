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

    public ArrayList<JSONObject> getAllHistory()
    {
        tables.put(history.tableName,null);
        condition += history.type + equal + history.constantAll;

        String query = createSelectQuery(selects,tables,condition);
        Cursor cursor = getReadableDatabase().rawQuery(query,null);
        ArrayList<JSONObject> jsons = toJson(cursor);
        return jsons;
    }
}
