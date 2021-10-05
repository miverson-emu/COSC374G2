package com.zombie.attackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveTaskToBack(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent keyEvent){
        System.out.println(keyEvent.getKeyCode());
        return false;
    }
}