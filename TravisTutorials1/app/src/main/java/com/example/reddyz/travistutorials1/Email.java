package com.example.reddyz.travistutorials1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Reddyz on 10-10-2016.
 */
public class Email extends AppCompatActivity implements View.OnClickListener {

    EditText emailText, introText, personNameText, greatThingsText, actionText, finalMessageText;
    String email, intro, personName, greatThings, action, finalMessage;
    Button sendEmailButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

        initializeVars();
        sendEmailButton.setOnClickListener(this);
    }

    private void initializeVars() {
        emailText = (EditText) findViewById(R.id.etEmails);
        introText = (EditText) findViewById(R.id.etIntro);
        personNameText = (EditText) findViewById(R.id.etName);
        greatThingsText = (EditText) findViewById(R.id.etGreatThings);
        actionText = (EditText) findViewById(R.id.etAction);
        finalMessageText = (EditText) findViewById(R.id.etMessage);
        sendEmailButton = (Button) findViewById(R.id.bSendEmail);
    }

    @Override
    public void onClick(View v) {
        convertAllTextFieldsToStrings();
        String emailAddress[] = {email};
        String composedMessage = " Hello there "
                + personName
                + "! You are Awesome... "
                + intro
                + ". Also I am inspired by you for your "
                + greatThings
                + ", and makes me super positive. I would love to "
                + action
                + ". Well, That's just a small dump of my thoughts and yeah, "
                + finalMessage
                + ". \nPleasure knowing you... "
                + "\n\n-Just Do It,\n Reddyz";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Awesomeeee");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, composedMessage);
        startActivity(emailIntent);
    }

    private void convertAllTextFieldsToStrings() {
        email = emailText.getText().toString();
        intro = introText.getText().toString();
        personName = personNameText.getText().toString();
        greatThings = greatThingsText.getText().toString();
        action = actionText.getText().toString();
        finalMessage = finalMessageText.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
