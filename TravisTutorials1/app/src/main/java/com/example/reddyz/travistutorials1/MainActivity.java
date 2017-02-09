package com.example.reddyz.travistutorials1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter;
    Button addButton, subButton;
    TextView resText;

    MediaPlayer mainSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;

        addButton = (Button) findViewById(R.id.addButton);
        subButton = (Button) findViewById(R.id.subButton);
        resText = (TextView) findViewById(R.id.resText);

        mainSong = MediaPlayer.create(this, R.raw.saddahaq_2);
        mainSong.start();
        mainSong.setLooping(true);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter++;
                        resText.setText("The Result is +"+counter);
                    }
                }
        );

        subButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter--;
                        resText.setText("The Result is -"+counter);
                    }
                }
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainSong.release();
    }
}
