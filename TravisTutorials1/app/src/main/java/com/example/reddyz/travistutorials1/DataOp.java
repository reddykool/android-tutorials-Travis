package com.example.reddyz.travistutorials1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Reddyz on 10-10-2016.
 */
public class DataOp extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView questionText, text;
    Button sendButton;
    RadioGroup rgOptions;
    String gotBread, sendData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);

        initializeVars();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", " Puru is...");
        String values = getData.getString("list", "1");
        if(values.contentEquals("1")) {
            questionText.setText(et);
        }

        //Bundle gotBasket = getIntent().getExtras();
        //gotBread = gotBasket.getString("foodKey");
        //questionText.setText(gotBread);
    }

    private void initializeVars() {
        questionText = (TextView) findViewById(R.id.tvQuestion);
        text = (TextView) findViewById(R.id.tvText);
        sendButton = (Button) findViewById(R.id.bSend);
        rgOptions = (RadioGroup) findViewById(R.id.rgAnswers);

        rgOptions.setOnCheckedChangeListener(this);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backPack = new Bundle();
        backPack.putString("answer", sendData);
        person.putExtras(backPack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbOne :
                sendData = "Probably Right!";
                break;
            case R.id.rbTwo :
                sendData = "Definitely Right!";
                break;
            case R.id.rbThree :
                sendData = "Bulls Eye!!!";
                break;
        }
        text.setText(sendData);
    }
}
