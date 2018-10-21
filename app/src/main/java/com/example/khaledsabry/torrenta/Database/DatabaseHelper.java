package com.example.khaledsabry.torrenta.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.khaledsabry.torrenta.MainActivity;

/**
 * this class for creating and updating the database and the structure of the database
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Torrenta.db";
    public static final int databaseVersion = 2;
    DatabaseTables.History history = new DatabaseTables.History();


    //quotes that is used to let the query function correctly
    static String quote = "\"";
    public final String createDataBaseSql = "CREATE TABLE "+ history.tableName+"(\n" +
            history.id+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            history.name+" TEXT,\n" +
            history.magnet+"Text, \n"+
            history.size +" TEXT, \n"+
            history.type+" integer,\n" +
            history.date+" TEXT NOT NULL\n" +
            ");";

    public  final String deleteDataBaseSql = "Drop Table " + history.tableName + " ; \n";


    @Override
    public void onCreate(SQLiteDatabase db) {

        //pass create the tables
        db.execSQL(createDataBaseSql);

    }

    public DatabaseHelper()
    {
        super(MainActivity.getActivity(),databaseName,null, databaseVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //pass drop the tables
        db.execSQL(deleteDataBaseSql);
        onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(deleteDataBaseSql);
        onCreate(db);
    }
}
