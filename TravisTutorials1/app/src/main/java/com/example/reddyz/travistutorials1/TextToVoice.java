package com.example.reddyz.travistutorials1;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class TextToVoice extends AppCompatActivity implements View.OnClickListener {

    EditText textIn;

    TextToSpeech tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texttovoice);

        textIn = (EditText) findViewById(R.id.etTextToVoice);
        Button b = (Button) findViewById(R.id.bTextToVoice);
        b.setOnClickListener(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String textStr = textIn.getText().toString();
        //tts.speak(textStr,TextToSpeech.QUEUE_FLUSH, null, "Reddyz_TTS" );
        tts.speak(textStr,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onPause() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
