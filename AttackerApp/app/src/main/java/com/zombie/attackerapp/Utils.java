package com.example.hijacker;

import android.util.Log;
import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;

import java.io.FileReader;
import java.io.IOException;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.Buffer;
import java.util.Iterator;


public class Utils extends Activity {


    //DEVICE FILE EXPLORER/ COM.EXAMPLE.HIJCAKER/X.JSON
    public static void writeNewJSONDataToJSONFile(Context context, String fileName, JSONObject json){
        JSONObject jsonDataStoredInDocument = readJSONFromFilesDir(context, fileName);
        JSONObject appendedJSONObject = appendMultipleEntriesToJSONObject(jsonDataStoredInDocument, json);
        Log.i("writeNewJSONDa...()", "JSONObject Appended: " + appendedJSONObject.toString());

        boolean fileWritten = writeToFilesDir(context, fileName, appendedJSONObject.toString());
        Log.i("writeNewJSONDa...()", "File Written: " + fileWritten);
    }

    public static JSONObject bundleToJSON(Bundle bundle) {

        JSONObject jo = new JSONObject();
        
        return jo;
    }

    public static JSONObject appendMultipleEntriesToJSONObject(JSONObject existingJSONObject, JSONObject additionalEntries) {
        //         APPEND NEW JSON TO EXISTING JSON
        Iterator<String> keys = additionalEntries.keys();
        while (keys.hasNext()) {
            String currentKey = keys.next();
            try {
                existingJSONObject.put(currentKey, additionalEntries.get(currentKey));
            } catch (JSONException e) {
                Log.i("Append", "JSON Exception");
            }
        }
        return existingJSONObject;
    }

    public static boolean writeToFilesDir(Context context, String fileName, String fileContents) {
        OutputStreamWriter os = null;
        try {
            os = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            os.write(fileContents);
            os.close();
            Log.i("WriteJSON", "Success!");
        } catch (FileNotFoundException e) {
            Log.i("Write", "File Not Found");
            return false;
        } catch (IOException e) {
            Log.i("WriteJSON", "IOException: JSON Object not written");
            return false;
        }
        return true;
    }


    public static void writeJSONToFilesDir(Context context, String fileName, JSONObject jsonData) throws JSONException{
       Log.i("WriteJSON...ctory", jsonData.toString(4));
        writeToFilesDir(context, fileName, jsonData.toString(4));
    }

    public static String readFromFilesDir(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();

        try {
            FileReader fr = new FileReader((new File(context.getFilesDir(), fileName)));
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            Log.i("writeToJSON()", "Read Exsiting JSON - File Not Found");
        } catch (IOException e) {
            Log.i("writeToJSON()", "Read Exsiting JSON - IOException");
        }
        return sb.toString();
    }

    public static JSONObject readJSONFromFilesDir(Context context, String fileName) {
        JSONObject jsonDataFromFile = null;
        try {
            String dataStoredInDocument = readFromFilesDir(context, fileName);
            jsonDataFromFile = new JSONObject(dataStoredInDocument);
        } catch (JSONException e) {
            Log.i("writeToJSON()", "Read Exsiting JSON - IOException");
            jsonDataFromFile = new JSONObject();
        }
        return jsonDataFromFile;
    }
}
