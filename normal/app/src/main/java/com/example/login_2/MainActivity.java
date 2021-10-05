package com.example.login_2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Vector<String> un = new Vector<>();
    Vector<String> pw = new Vector<>();
    String disp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submit_clicked(View view) {

        String un = ((EditText)findViewById(R.id.userName)).getText().toString();
        String pw = ((EditText)findViewById(R.id.pw_id)).getText().toString();
        String st = "Invalid Username / Password";




        if (un.equals("")) { // invalid username
//            Toast.makeText(this,  "Enter valid username",Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.id_submit_text)).setText(st);

            return;
        }
        if (pw.equals("")) {
//            Toast.makeText(this,  "Enter valid password",Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.id_submit_text)).setText(st);
            return;
        }
        ((TextView)findViewById(R.id.id_submit_text)).setText("Data accepted");

        this.un.add(un); // store these for attack demo
        this.pw.add(pw); // store these for attack demo
        disp += un + "    " + pw + "\n";

        ((EditText)findViewById(R.id.userName)).setText("");
        ((EditText)findViewById(R.id.pw_id)).setText((""));
//        ((EditText)findViewById(R.id.disp_id)).setText(disp);

        System.out.println("stored: " + un + "\t" + pw);
//        Toast.makeText(this,  "System is currently down, please try again soon",Toast.LENGTH_LONG).show();

    } // end submit_clicked




} // end class