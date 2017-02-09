package com.example.reddyz.travistutorials1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Reddyz on 31-10-2016.
 */

public class HttpJson extends AppCompatActivity{

    HttpURLConnection httpCon;
    TextView httpDataText;
    JSONObject json;

    //final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
    //final static String id = "reddykool";
    //final static String parseFor = "text";
    final static String URL = "http://donor.ua/api/cities?sign=9u1AARsgKybup3vz9CaQnw==ivbCaKmrWpgz";
    final static String id = "";
    final static String parseFor = "nameEn";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpjson);

        httpDataText = (TextView) findViewById(R.id.tvHttpJsonResponse);
        httpCon = null;
        new JsonRead().execute(parseFor);
    }

    public JSONObject lastTweet(String username) throws JSONException, IOException {
        StringBuilder urlString = new StringBuilder(URL);
        urlString.append(username);

        URL url = new URL(urlString.toString());
        httpCon = (HttpURLConnection) url.openConnection();
        //httpCon.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        httpCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        httpCon.connect();
        int status = httpCon.getResponseCode();
        {
            InputStream in = httpCon.getInputStream();
            String data = convertStreamToString(in);
            JSONObject jsonObj = new JSONObject(data);
            JSONArray list = jsonObj.getJSONArray("value");
            JSONObject last = (list.getJSONObject(list.length()-2));
            return last;
        }
    }

    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public class JsonRead extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                json = lastTweet(id);
                //return json.getString(params[0]);
                StringBuilder result = new StringBuilder();
                result.append(" Last entity is : \n  ");
                result.append(json.toString());
                result.append("\n\n");
                result.append(" Parsing for '"+params[0]+"': ");
                result.append(json.getString(params[0]));
                return result.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            //return null;
        }

        @Override
        protected void onPostExecute(String result) {
            httpDataText.setText(result);
        }
    }
}
