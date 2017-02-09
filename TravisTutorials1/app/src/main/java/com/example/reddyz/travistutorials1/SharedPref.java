package com.example.reddyz.travistutorials1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Reddyz on 13-10-2016.
 */
public class SharedPref extends AppCompatActivity implements View.OnClickListener {

    TextView tvLoadData;
    EditText etInputData;
    static final String spFilename = "MySharedPrefData";
    static int saveCount;
    static String SP_KEY_DATA ="SharedString";
    static String SP_KEY_COUNT ="SaveCount";
    SharedPreferences spData;

    static final String TAG="Reddyz-Log";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpref);

        initializeVars();
        spData = getSharedPreferences(spFilename, 0);
        Log.i(TAG, "SharedPref:onCreate, saveCount : " + saveCount);
    }

    private void initializeVars() {
        Button bSave, bLoad;

        etInputData = (EditText) findViewById(R.id.etSPInput);
        tvLoadData = (TextView) findViewById(R.id.tvSPData);
        bSave = (Button)findViewById(R.id.bSPSave);
        bLoad = (Button) findViewById(R.id.bSPLoad);

        bSave.setOnClickListener(this);
        bLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSPSave :
                String inputData = etInputData.getText().toString();
                SharedPreferences.Editor spEditor = spData.edit();
                spEditor.putString(SP_KEY_DATA, inputData);
                saveCount++;
                spEditor.putInt(SP_KEY_COUNT, saveCount);
                spEditor.commit();
                Log.i(TAG, "SharedPref:onClick(bSPSave), inputData : " + inputData + " \n saveCount : " + saveCount);
                break;
            case R.id.bSPLoad :
                //To get latest data
                spData = getSharedPreferences(spFilename, 0);
                String data = spData.getString(SP_KEY_DATA, "Could not load data");
                int count = spData.getInt(SP_KEY_COUNT, -1);
                tvLoadData.setText("Load data:\n" + data + "\n" + count);
                Log.i(TAG, "SharedPref:onClick(bSPLoad), data : " + data + " \n saveCount : " + count);
                break;
        }

    }
}
