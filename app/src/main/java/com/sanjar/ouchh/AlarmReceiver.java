package com.sanjar.ouchh;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import java.net.URI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/*
Performs the following three actions at set alarm time:
1. Wake up message
2. Alarm ringer
3. Access the URL which will trigger Node-RED server in Raspberry pi which
   will launch python program to active servro motor
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        //Wake up message gets sent to main class
        MainActivity.getTextView2().setText("Wake up sunshine..... ;)");

        //Web GET request opens
        Runtime x = Runtime.getRuntime();
        String url = "http://137.48.186.21:1880/servotest";
        try {
            x.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Alarm sound
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        //Web request
        /*URL url = null;
        try {
            url = new URL("http://137.48.191.167:1880/servotest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();

        }*/
    }
}
