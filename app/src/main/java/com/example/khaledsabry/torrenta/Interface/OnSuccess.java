package com.example.khaledsabry.torrenta.Interface;


import com.example.khaledsabry.torrenta.items.HistoryItem;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public interface OnSuccess {



    interface bool
    {
        void onSuccess(boolean state);
    }

    interface array
    {
        void onSuccess(ArrayList<java.lang.Object> historyItems);
    }
}
