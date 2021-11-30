package com.example.login_2;

/*
This is the new app...
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    JSONObject intent_data = new JSONObject();

    Vector<String> username_vector = new Vector<>();
    Vector<String> password_vector = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submit_clicked(View view) {

        String username = ((EditText)findViewById(R.id.userName)).getText().toString();
        String password = ((EditText)findViewById(R.id.pw_id)).getText().toString();

        setIntentData(username, password);

        ((EditText)findViewById(R.id.userName)).setText("");
        ((EditText)findViewById(R.id.pw_id)).setText((""));

        System.out.println("stored: " + username + "\t" + password);

    }

    public void setIntentData(String username, String password) {
        //STORE INTENT DATA =============================================

        //VECTORS
        this.username_vector.add(username); // store these for attack demo
        this.password_vector.add(password); // store these for attack demo

        //JSON
        try{
            this.intent_data.put("username", username);
            this.intent_data.put("password", password);
        }
        catch (JSONException e) {
            Log.i("Error", "JSON Exception");
        }
    }

    public void butt_intent(View view) {

        setIntentData(((EditText)findViewById(R.id.userName)).getText().toString(), ((EditText)findViewById(R.id.pw_id)).getText().toString());
        Intent sendIntent = new Intent("com.example.normalapp2.LOGIN");

// MIGITGATION TECHNIQUE: ENCYRPTION ==========================================
        sendIntent.putExtra(Utils.b64encrypt("vectorinfo_username"), Utils.b64encrypt(this.username_vector.get(0)));
        sendIntent.putExtra(Utils.b64encrypt("vectorinfo_password"), Utils.b64encrypt(this.password_vector.get(0)));
// MIGITGATION TECHNIQUE: ENCYRPTION ==========================================

// ORIGINAL TECHNIQUE =========================================================
//        sendIntent.putExtra("vectorinfo_username", this.username_vector.get(0));
//        sendIntent.putExtra("vectorinfo_password", this.password_vector.get(0));
// ORIGINAL TECHNIQUE =========================================================


        //SEND INTENT
        Log.i("SendIntent", sendIntent.getExtras().toString());

        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException e) { System.out.println("There was a problem sending this intent."); }
    }
}