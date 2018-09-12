package com.example.khaledsabry.torrenta.Controllers;

import com.example.khaledsabry.torrenta.Database.DatabaseController.DatabaseController;
import com.example.khaledsabry.torrenta.Database.DatabaseTables;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    //variable to the database controller to use it latter in the childeren of the parent controller class
    protected DatabaseController databaseController = new DatabaseController();
    protected DatabaseTables.History history = new DatabaseTables.History();
    protected int all = history.constantAll;
    protected int movie = history.constantMovie;
    protected int tv = history.constantTv;
    protected int games = history.constantGames;
    protected int software = history.constantSoftware;





}
