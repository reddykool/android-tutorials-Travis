package com.example.reddyz.travistutorials1;

import android.app.ListActivity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Reddyz on 09-10-2016.
 */

public class Menu extends AppCompatActivity{


    static final String TAG="Reddyz-log";
    String listItemArray[] = {"MainActivity", "TextPlay", "Email","Camera",
            "Data","GFX","GFXSurface","SoundStuff", "Slider", "Tabs",
            "SimpleBrowser", "Flipper", "SharedPref", "InternalData", "ExternalData",
            "SqlLite", "Accelerate", "HttpSample", "HttpJson", "WeatherXmlParsing",
            "GLExample", "Voice", "TextToVoice", "StatusBar", "SeekBarVolume"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //Full screen mode - Not working :(
        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */

        ListAdapter menuListAdapter = new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, listItemArray);
        ListView menuListView = (ListView)findViewById(R.id.lvMainMenu);
        menuListView.setAdapter(menuListAdapter);

        menuListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String className = listItemArray[position];
                        Log.i(TAG, "OnItemClicked Anonymous class: "+className);
                        try {
                            Class newClass = Class.forName("com.example.reddyz.travistutorials1." + className);
                            Intent newIntent = new Intent(Menu.this, newClass);
                            startActivity(newIntent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater openMenu = getMenuInflater();
        openMenu.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutUs :
                Intent aboutIntent = new Intent(this,AboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.preferences :
                Intent prefIntent = new Intent(this, Prefs.class);
                startActivity(prefIntent);
                break;
            case R.id.exit :
                finish();
                break;
        }
        return false;
    }
}

//As per Travis Tutorial- Old version of android?
/*
public class Menu extends ListActivity {

    String listItemArray[] = {"MainActivity", "TextPlay", "Email","Camera",
                              "Data","example5","example6","example7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, listItemArray));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String className = listItemArray[position];
        try {
            Class newClass = Class.forName("com.example.reddyz.travistutorials1." + className);
            Intent newIntent = new Intent(Menu.this, newClass);
            startActivity(newIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
*/