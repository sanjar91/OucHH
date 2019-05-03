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
import java.io.OutputStream;

/*
This is where I will add an intend to send to my raspberry pi using ifttt
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        //Wake up message gets sent to main class
        MainActivity.getTextView2().setText("Wake up sunshine..... ;)");
    private static final String POST_URL = "http://137.48.186.21:1880/servotest"";
        private static final String GET_URL = "http://localhost:9090/SpringMVCExample";
        private static final String POST_PARAMS = "";
        //Web GET request opens
        Runtime x = Runtime.getRuntime();
        String url = "http://137.48.186.21:1880/servotest";
        //try {
        //    x.exec("rundll32 url.dll,FileProtocolHandler " + url);
        //} catch (IOException e) {
        //    e.printStackTrace();
       // }
        sendGET();
		sendPOST();



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
        //Method to post request:
        private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("POST Request sent");
		} else {
			System.out.println("POST request not worked");
		}
	}
    //Method to Get request:
    private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			System.out.println("POST Request sent");
		} else {
			System.out.println("GET request not worked");
		}

	}
    
}
