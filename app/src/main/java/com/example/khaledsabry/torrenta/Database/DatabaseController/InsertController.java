package com.example.khaledsabry.torrenta.Database.DatabaseController;

import android.content.ContentValues;

import com.example.khaledsabry.torrenta.Inteface.OnDatabaseSuccess;

import java.util.Date;

import static com.example.khaledsabry.torrenta.Database.DatabaseTables.*;

public class InsertController extends DatabaseController {

    public void AddHistory(String name, int type, OnDatabaseSuccess.number listener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(History.name, name);
        contentValues.put(History.type, type);
        Date date = new Date();
        long d = date.getTime();
        contentValues.put(History.date, d);

        long result = getWritableDatabase().insert(History.tableName, null, contentValues);
        listener.onSuccess((int) result);


    }


}
