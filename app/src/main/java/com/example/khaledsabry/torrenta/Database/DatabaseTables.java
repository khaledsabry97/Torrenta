package com.example.khaledsabry.torrenta.Database;

public class DatabaseTables {

    public static class History {
        public final String tableName = "`History`";
        public final String id = "`id`";
        public final String name = "`name`";
        public final String type = "`type`";
        public final String date = "`date`";
        public final String size = "`size`";
        public final String magnet = "`magnet`";

        public final int constantAll = 0;
        public final int constantMovie = 1;
        public final int constantTv = 2;
        public final int constantGames = 3;
        public final int constantSoftware = 4;

    }
}
