package com.example.reddyz.travistutorials1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by Reddyz on 10-10-2016.
 */
public class TextPlay extends AppCompatActivity implements View.OnClickListener {

    Button tryCmdButton;
    ToggleButton toggleButton;
    EditText inputCmd;
    TextView display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        initializeVars();
        tryCmdButton.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
    }

    private void initializeVars() {
        tryCmdButton = (Button) findViewById(R.id.tryButton);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        inputCmd = (EditText) findViewById(R.id.textCommand);
        display = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toggleButton :
                {
                    if(toggleButton.isChecked()) {
                        inputCmd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    } else {
                        inputCmd.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                }
                break;

            case R.id.tryButton :
                {
                    String inputCmdText = inputCmd.getText().toString();
                    display.setText(inputCmdText);
                    if(inputCmdText.contentEquals("left")) {
                        display.setGravity(Gravity.LEFT);
                    } else if(inputCmdText.contentEquals("center")) {
                        display.setGravity(Gravity.CENTER);
                    } else if(inputCmdText.contentEquals("right")) {
                        display.setGravity(Gravity.RIGHT);
                    } else if(inputCmdText.contentEquals("blue")) {
                        display.setTextColor(Color.BLUE);
                    } else if(inputCmdText.contentEquals("WTF")) {
                        Random crazy = new Random();
                        display.setText("WTF!!!");
                        display.setTextSize(crazy.nextInt(75));
                        display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));
                        switch (crazy.nextInt(3)) {
                            case 0:
                                display.setGravity(Gravity.LEFT);
                                break;
                            case 1:
                                display.setGravity(Gravity.CENTER);
                                break;
                            case 2 :
                                display.setGravity(Gravity.RIGHT);
                                break;
                        }
                    } else {
                        display.setText("Invalid");
                        display.setTextColor(Color.RED);
                        display.setGravity(Gravity.CENTER);
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }
}
