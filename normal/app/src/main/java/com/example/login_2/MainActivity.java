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

    String disp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submit_clicked(View view) {

        String username = ((EditText)findViewById(R.id.userName)).getText().toString();
        String password = ((EditText)findViewById(R.id.pw_id)).getText().toString();
        String st = "Invalid Username / Password";

        //ERROR HANDLING =============================================
        if (username.equals("")) {
//            Toast.makeText(this,  "Enter valid username",Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.id_submit_text)).setText(st);
            Log.e("SignInError", "Invalid Username");

            return;
        }
        if (password.equals("")) {
//            Toast.makeText(this,  "Enter valid password",Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.id_submit_text)).setText(st);
            Log.e("SignInError", "Invalid Password");
            return;
        }
//        ((TextView)findViewById(R.id.id_submit_text)).setText("Data accepted");

        setIntentData(username, password);

        // COMPLETE (!! DO WE NEED THIS?)=============================================
//        disp += username + "    " + password + "\n";

        ((EditText)findViewById(R.id.userName)).setText("");
        ((EditText)findViewById(R.id.pw_id)).setText((""));
//        ((EditText)findViewById(R.id.disp_id)).setText(disp);

        System.out.println("stored: " + username + "\t" + password);
//        Toast.makeText(this,  "System is currently down, please try again soon",Toast.LENGTH_LONG).show();

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

        // MIGITGATION TECHNIQUE ==========================================
        //ENCYRPTION

//        sendIntent.putExtra(Utils.b64encrypt("vectorinfo_username"), Utils.b64encrypt(this.username_vector.get(0)));
//        sendIntent.putExtra(Utils.b64encrypt("vectorinfo_password"), Utils.b64encrypt(this.password_vector.get(0)));
////        sendIntent.putExtra(Utils.b64encrypt("jsoninfo"), Utils.b64encrypt(this.intent_data.toString()));

        // MIGITGATION TECHNIQUE ==========================================

        // ORIGINAL TECHNIQUE ==========================================

        sendIntent.putExtra("vectorinfo_username", this.username_vector.get(0));
        sendIntent.putExtra("vectorinfo_password", this.password_vector.get(0));
//        sendIntent.putExtra("jsoninfo", this.intent_data.toString());

        // ORIGINAL TECHNIQUE ==========================================

        Log.i("SendIntent", sendIntent.getExtras().toString());

        // invoke the intent
        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException e) { System.out.println("There was a problem sending this intent."); }
    }
}