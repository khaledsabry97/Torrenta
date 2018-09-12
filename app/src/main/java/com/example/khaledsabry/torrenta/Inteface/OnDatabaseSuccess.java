package com.example.khaledsabry.torrenta.Inteface;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 01-Aug-18.
 */

public interface OnDatabaseSuccess {


    interface array {
        void onSuccess(ArrayList<JSONObject> jsonObjects);

    }

    interface bool {
        void onSuccess(boolean state);

    }
    interface number {
        void onSuccess(int state);

    }
}
