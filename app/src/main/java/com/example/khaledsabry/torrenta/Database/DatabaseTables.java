package com.example.khaledsabry.torrenta.Database;

public class DatabaseTables {
    //quoutes that is used to let the query function correctly
    static String quote = "\"";
    public static final String createDataBaseSql = "CREATE TABLE "+ History.tableName+"(\n" +
            History.id+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            History.name+" TEXT,\n" +
           History.type+" integer,\n" +
            History.date+" TEXT NOT NULL\n" +
            ");";

    public static final String deleteDataBaseSql = "Drop Table " + History.tableName + " ; \n";

    public static class History {
        public static final String tableName = "`History`";
        public static final String id = "`id`";
        public static final String name = "`name`";
        public static final String type = "`type`";
        public static final String date = "`date`";

        public  final int constantAll = 0;
        public  final int constantMovie = 1;
        public  final int constantTv = 2;
        public  final int constantGames = 3;
        public  final int constantSoftware = 4;

    }
}
