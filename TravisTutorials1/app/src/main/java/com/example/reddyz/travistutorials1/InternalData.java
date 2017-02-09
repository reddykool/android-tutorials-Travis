package com.example.reddyz.travistutorials1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Reddyz on 13-10-2016.
 */
public class InternalData extends AppCompatActivity implements View.OnClickListener {

    TextView tvLoadData;
    EditText etInputData;
    FileOutputStream fileOutStream;
    String FILENAME = "InternalFilexoy";

    static final String TAG="Reddyz-Log";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpref);
        initializeVars();
    }

    private void initializeVars() {
        Button bSave, bLoad;

        etInputData = (EditText) findViewById(R.id.etSPInput);
        tvLoadData = (TextView) findViewById(R.id.tvSPData);
        bSave = (Button)findViewById(R.id.bSPSave);
        bLoad = (Button) findViewById(R.id.bSPLoad);

        bSave.setOnClickListener(this);
        bLoad.setOnClickListener(this);

        /*
        try {
            fileOutStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSPSave:
                String inputData = etInputData.getText().toString();
                /*
                File file = new File(FILENAME);
                try {
                    fileOutStream = new FileOutputStream(file);
                    fileOutStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                try {
                    String dir = getFilesDir().getAbsolutePath();
                    fileOutStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fileOutStream.write(inputData.getBytes());
                    fileOutStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bSPLoad:
                new LoadData().execute(FILENAME);
                break;
        }
    }

    public class LoadData extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected void onProgressUpdate(Integer... values) {
            pDialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(InternalData.this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setMax(100);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String resultStr) {
            tvLoadData.setText(resultStr);
            pDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;
            FileInputStream fileInStream = null;
            for (int i=0;i<10;i++) {
                publishProgress(5);
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileInStream = openFileInput(FILENAME);
                publishProgress(10);

                int readSize = fileInStream.available();
                Log.i(TAG, "file read bytes available: " + readSize);
                if( readSize != 0 ) {
                    byte[] dataArray = new byte[readSize];
                    publishProgress(10);
                    while (fileInStream.read(dataArray) != -1) {
                        Log.i(TAG, "Reading from file");
                        collected = new String(dataArray);
                    }
                    publishProgress(50);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if( fileInStream!= null)
                        fileInStream.close();
                    publishProgress(30);
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(100);
            return null;
        }
    }
}
