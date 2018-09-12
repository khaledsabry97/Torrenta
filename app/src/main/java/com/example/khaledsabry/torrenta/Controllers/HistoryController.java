package com.example.khaledsabry.torrenta.Controllers;

import com.example.khaledsabry.torrenta.Inteface.OnDatabaseSuccess;
import com.example.khaledsabry.torrenta.Inteface.OnSuccess;

public class HistoryController extends Controller implements OnDatabaseSuccess.number {

private OnSuccess.bool listener = new OnSuccess.bool() {
    @Override
    public void onSuccess(boolean state) {

    }
};

    public void addAllToHistory(String name, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name, all, this);


    }
    public void addMovieToHistory(String name, final OnSuccess.bool listener)
    {
        this.listener = listener;

        databaseController.insertController().AddHistory(name, movie, this);


    }

    public void addTvToHistory(String name, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name, tv, this);

    }

    public void addGamesToHistory(String name, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name, games, this);


    }

    public void addSoftwareToHistory(String name, final OnSuccess.bool listener)
    {
        this.listener = listener;
        databaseController.insertController().AddHistory(name, software, this);


    }


    @Override
    public void onSuccess(int state) {
        if(state != -1)
            listener.onSuccess(true);
        else
            listener.onSuccess(false);
    }
}
