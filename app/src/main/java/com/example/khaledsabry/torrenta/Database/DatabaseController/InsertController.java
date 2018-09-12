package com.example.khaledsabry.torrenta.Database.DatabaseController;

import android.content.ContentValues;

import com.example.khaledsabry.torrenta.Interface.OnDatabaseSuccess;

import java.util.Date;

public class InsertController extends DatabaseController {

    public void AddHistory(String name, String size, int type, OnDatabaseSuccess.number listener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(history.name, name);
        contentValues.put(history.type, type);
        Date date = new Date();
        long d = date.getTime();
        contentValues.put(history.date, d);
        contentValues.put(history.size, size);
        long result = getWritableDatabase().insert(history.tableName, null, contentValues);
        listener.onSuccess((int) result);


    }


}
