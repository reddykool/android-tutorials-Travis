package com.example.reddyz.travistutorials1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class StatusBar extends AppCompatActivity implements View.OnClickListener {

    NotificationManager nm;
    static final int mID =69;
    static int count=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);
        Button b = (Button) findViewById(R.id.bStatusBar);
        b.setOnClickListener(this);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(mID);
    }

    @Override
    public void onClick(View v) {
        Intent resultIntent = new Intent(this, StatusBar.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        count++;
        String body = "You are a Millionaire... Bravo-" + count;
        String title = "Congratulations!-"+count;

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.drawable.ic_stat_name);
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(body);
        nBuilder.setContentIntent(pendingIntent);

        nm.notify(mID, nBuilder.build());
        //finish();
    }
}
