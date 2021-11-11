package com.zombie.attackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView Textv;

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println(getIntent().getStringExtra("info"));
        Textv = (TextView) findViewById(R.id.msg);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        System.out.println(b);
        if (b != null) {
            String j = (String) b.get("info");
            System.out.println(b);
            System.out.println("make it here");
            Textv.setText(j);
        }
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent keyEvent){
        System.out.println(keyEvent.getKeyCode());
        return false;
    }
}