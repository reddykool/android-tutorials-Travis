package com.example.reddyz.travistutorials1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Reddyz on 10-10-2016.
 */
public class Data extends AppCompatActivity implements View.OnClickListener {
    Button startButton, startResButton;
    EditText sendText;
    TextView answerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive);

        initializeVars();
    }

    private void initializeVars() {
        startButton = (Button) findViewById(R.id.bStartAct);
        startResButton = (Button) findViewById(R.id.bStartActforRes);
        sendText = (EditText) findViewById(R.id.etSend);
        answerText = (TextView) findViewById(R.id.tvReceived);

        startButton.setOnClickListener(this);
        startResButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStartAct :
                String bread = sendText.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("foodKey",bread);
                Intent a = new Intent(Data.this, DataOp.class);
                a.putExtras(basket);
                startActivity(a);;
                break;
            case R.id.bStartActforRes :
                Intent i = new Intent(Data.this, DataOp.class);
                startActivityForResult(i, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle basket = data.getExtras();
            String str = basket.getString("answer");
            answerText.setText(str);
        }
    }
}
