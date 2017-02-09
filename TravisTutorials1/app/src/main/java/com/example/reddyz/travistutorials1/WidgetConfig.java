package com.example.reddyz.travistutorials1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Reddyz on 01-11-2016.
 */

public class WidgetConfig extends AppCompatActivity implements View.OnClickListener{

    EditText widgetName;
    AppWidgetManager awm;
    Context ctx;
    int awID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetconfig);

        Button b = (Button) findViewById(R.id.bWidgetConfigSet);
        b.setOnClickListener(this);

        Toast.makeText(this,"Widget Configure", Toast.LENGTH_LONG).show();
        ctx = WidgetConfig.this;
        widgetName = (EditText) findViewById(R.id.etWidgetConfigName);

        //Getting info about the widget that was launched !!!
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null) {
            awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        } else {
            finish();
        }

        awm = AppWidgetManager.getInstance(ctx);
    }

    @Override
    public void onClick(View v) {
        String str = widgetName.getText().toString();
        RemoteViews views = new RemoteViews(ctx.getPackageName(), R.layout.sample_app_widget);
        views.setTextViewText(R.id.tvWidgetInput, str);

        Intent i = new Intent(ctx, Splash.class);
        PendingIntent pi = PendingIntent.getActivity(ctx, 0, i, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text,pi);

        awm.updateAppWidget(awID, views);

        Intent result = new Intent();
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
        setResult(RESULT_OK, result);

        finish();
    }
}
