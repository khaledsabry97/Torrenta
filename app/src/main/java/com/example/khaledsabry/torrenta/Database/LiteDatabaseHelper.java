package com.example.khaledsabry.torrenta.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.khaledsabry.torrenta.MainActivity;


public class LiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Torrenta.db";
    public static final int databaseVersion = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {

        //pass create the tables
        db.execSQL(DatabaseTables.createDataBaseSql);

    }

    public LiteDatabaseHelper()
    {
        super(MainActivity.getActivity(),databaseName,null, databaseVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //pass drop the tables
        db.execSQL(DatabaseTables.deleteDataBaseSql);
        onCreate(db);
    }

    
}
