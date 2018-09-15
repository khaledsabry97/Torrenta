package com.example.khaledsabry.torrenta.Database.DatabaseController;

import com.example.khaledsabry.torrenta.Database.DatabaseTables;
import com.example.khaledsabry.torrenta.Database.DatabaseTables.History;

public class DeleteController extends DatabaseController {
    private String table = "";
    private String condition = "";


    public void historyDeleteItem(String id) {
table = history.tableName;
condition += history.id +equal+ id;

getWritableDatabase().execSQL(createDeleteQuery(table,condition));


    }
}
