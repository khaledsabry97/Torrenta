package com.example.khaledsabry.torrenta;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.khaledsabry.torrenta.Fragments.MainFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    //to get the main activity in the app
    private static MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    public MainFragment mainFragment = MainFragment.newInstance();

    //reference to the main activity
    public static MainActivity getActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadFragmentWithReturn(R.id.main_container, mainFragment);

    }


    //load fragment with ability to return
    public static void loadFragmentWithReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).addToBackStack(null).commit();
    }

    //load fragment with no ability to return

    public static void loadFragmentNoReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).commit();
    }



}
