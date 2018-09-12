package com.example.khaledsabry.torrenta.Interface;


import com.example.khaledsabry.torrenta.items.HistoryItem;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public interface OnSuccess {

    interface Object
    {
        void onSuccess(Object state);
        void onSuccess(Integer num);
    }
    interface Json {
        void onSuccess(JSONObject jsonObject);
    }
     interface string
    {
        void onSuccess(String word);
    }

    interface bool
    {
        void onSuccess(boolean state);
    }

    interface arrayMap
    {
        void onSuccess(String key, ArrayList<Object> values);
    }

    interface objects
    {
        void onSuccess(ArrayList<java.lang.Object> objects);
    }

    interface name
    {
        void onSuccess(String name);
    }

    interface array
    {
        void onSuccess(ArrayList<java.lang.Object> historyItems);
    }
}
