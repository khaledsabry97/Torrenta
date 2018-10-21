package com.example.khaledsabry.torrenta.Database.DatabaseController;

import android.content.ContentValues;

import com.example.khaledsabry.torrenta.Interface.OnDatabaseSuccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertController extends DatabaseController {

    public void AddHistory(String name,String magnet, String size, int type, OnDatabaseSuccess.number listener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(history.name, name);
        contentValues.put(history.type, type);
        contentValues.put(history.magnet,magnet);
        contentValues.put(history.size, size);

        //formate and add date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date date = calendar.getTime();
        String today = formatter.format(date);
        contentValues.put(history.date, today);

        long result = getWritableDatabase().insert(history.tableName, null, contentValues);
        listener.onSuccess((int) result);


    }


}
