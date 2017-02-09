package com.example.reddyz.travistutorials1;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Reddyz on 12-10-2016.
 */
public class Tabs extends AppCompatActivity implements View.OnClickListener{


    TabHost tabHost;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        Button bAddNewTab = (Button)findViewById(R.id.bAddTab);
        Button bStart = (Button)findViewById(R.id.bStartWatch);
        Button bStop = (Button)findViewById(R.id.bStopWatch);
        showResults = (TextView)findViewById(R.id.tvShowResult);

        bAddNewTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("StopWatch");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("TBD");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("Add-a-Tab");
        tabHost.addTab(tabSpec);

        start = stop = 0;
        //tabHost.addTab(tabSpec);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddTab :
                TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
                tabSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("Created New Tab !");
                        return (text);
                    }
                });
                tabSpec.setIndicator("New");
                tabHost.addTab(tabSpec);
                break;

            case R.id.bStartWatch :
                start = System.currentTimeMillis();
                break;

            case R.id.bStopWatch :
                stop = System.currentTimeMillis();
                if(start!=0) {
                    long result = stop - start;
                    int millis = (int)result;
                    int seconds = (int)result/1000;
                    int minutes = seconds/60;

                    millis %= 1000;
                    seconds %= 60;
                    showResults.setText(String.format("%02d:%02d:%04d ", minutes, seconds, millis));
                }
                break;
        }

    }
}
