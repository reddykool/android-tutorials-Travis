package com.example.reddyz.travistutorials1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;

/**
 * Created by Reddyz on 28-10-2016.
 */

public class HttpSample extends AppCompatActivity implements View.OnClickListener {

    EditText inputCmdText;
    Button submitButton;
    ProgressBar pb;
    TextView responseText;

    boolean useAsyncTask = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpsample);

        inputCmdText = (EditText) findViewById(R.id.etHttpInputCmd);
        submitButton = (Button) findViewById(R.id.bHttpSubmit);
        pb = (ProgressBar) findViewById(R.id.pbHttpProgBar);
        responseText = (TextView) findViewById(R.id.tvHttpResponse);

        pb.setVisibility(View.GONE);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String inputUrl = inputCmdText.getText().toString();
        if(inputUrl.equals("")) {
            inputUrl = "http://reddykool.blogspot.in/";
        }

        if(useAsyncTask) {
            new HttpDataProcessor().execute(inputUrl);
        } else {
            executeOnMainThread(inputUrl);
        }
    }

    private void bypassStrictMainThreadPolicy() {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    // If you want to execute HTTP Requests on main thread
    // IT IS NOT RECOMMENDED  TO DO SO. This is just for sample.
    // Hence use AnycTask to do the same in a seperate thread without blocking main thread.

    private void executeOnMainThread(String urlString) {
        String response="Nothing...";
        HttpURLConnection urlConnection = null;
        InputStream inStream = null;

        bypassStrictMainThreadPolicy();

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            //inStream = new BufferedInputStream(urlConnection.getInputStream());
            inStream = urlConnection.getInputStream();
            response = convertStreamToString(inStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            response = e.toString();
        } catch (UnknownServiceException e) {
            e.printStackTrace();
            response = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            response = e.toString();
        } finally {
            responseText.setText(response);
            if(inStream != null)
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(urlConnection != null)
                urlConnection.disconnect();
        }
    }

    @NonNull
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

    public class HttpDataProcessor extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String response="Nothing...";
            HttpURLConnection urlConnection = null;
            InputStream inStream = null;
            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                publishProgress(25);
                //inStream = new BufferedInputStream(urlConnection.getInputStream());
                inStream = urlConnection.getInputStream();
                publishProgress(50);
                response = convertStreamToString(inStream);
                publishProgress(75);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                response = e.toString();
            } catch (UnknownServiceException e) {
                e.printStackTrace();
                response = e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                response = e.toString();
            } finally {
                if(inStream != null)
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if(urlConnection != null)
                    urlConnection.disconnect();
                publishProgress(100);
                return response;
            }
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String response) {
            pb.setVisibility(View.GONE);
            responseText.setText(response);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Integer prog = values[0] ;
            pb.setProgress(prog);
        }
    }

}
