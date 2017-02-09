package com.example.reddyz.travistutorials1;

import android.app.AlarmManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class SampleAppWidget extends AppWidgetProvider {
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context,"Bye Bye", Toast.LENGTH_LONG).show();
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Random rNum = new Random();
        int num = rNum.nextInt(1000000);
        String randomStr = String.valueOf(num);

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sample_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.tvWidgetNum,randomStr);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Toast.makeText(context,"onUpdate", Toast.LENGTH_LONG).show();
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(context,"onEnabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(context,"onDisabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(context,"onReceive", Toast.LENGTH_LONG).show();
    }
}

