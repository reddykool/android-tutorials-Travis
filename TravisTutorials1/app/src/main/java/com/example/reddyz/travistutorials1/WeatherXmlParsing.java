package com.example.reddyz.travistutorials1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Reddyz on 31-10-2016.
 */


//Weather API from Google: http://www.google.com/ig/api?weather=<city>,<state>

/* IMPORTANT : Google has closed their Weather and Cloud solutions since 4th of June 2015 and recommended to
   use OpenWeatherMap with the Google Maps JavaScript API as an alternative solution.
   Call API format::
   http://api.openweathermap.org/data/2.5/weather?q=<city>&mode=<xml/html>&units=<metric/imperial>&APPID=<API KEY>
   default mode = Json
   default units = kelvin
   APPID is must (Register with openweathermap.org to get API key
   Example API for Reddyz :
   http://api.openweathermap.org/data/2.5/weather?q=Bangalore&mode=xml&units=metric&APPID=82971f23acc532e8fd20cd56c5f8fd02
*/

public class WeatherXmlParsing extends AppCompatActivity implements View.OnClickListener {

    //static final String baseURL ="http://www.google.com/ig/api?weather="; - Not SUpported anyore by Google
    static final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    static final String API_KEY="82971f23acc532e8fd20cd56c5f8fd02";

    TextView cityText, countryText, temprText, weatherText, humidText;
    EditText cityTextInput;

    HandleXMLInfo xmlInfoHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.xmlparsing);
        cityTextInput = (EditText) findViewById(R.id.etWXMLPCity);

        Button getWeather = (Button)findViewById(R.id.bWXMLPGetWeather);
        Button getWeatherAsync = (Button)findViewById(R.id.bWXMLPGetWeatherAsync);
        cityText = (TextView) findViewById(R.id.tvWXMLPCity);
        countryText = (TextView) findViewById(R.id.tvWXMLPCountry);
        temprText = (TextView) findViewById(R.id.tvWXMLPTemperature);
        weatherText = (TextView) findViewById(R.id.tvWXMLPWeather);
        humidText = (TextView) findViewById(R.id.tvWXMLPHumidity);

        getWeather.setOnClickListener(this);
        getWeatherAsync.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String city = cityTextInput.getText().toString();
        switch (v.getId()) {
            case R.id.bWXMLPGetWeather :
                getWeatherInfoOnMainThread(city);
                break;

            case R.id.bWXMLPGetWeatherAsync :
                new GetWeatherInfo().execute(city);
                break;
        }

    }

    private void getWeatherInfoOnMainThread(String city) {
        StringBuilder sbUrl = new StringBuilder(baseURL);
        sbUrl.append(city);
        sbUrl.append("&mode=xml");        // To get response in XML Format
        sbUrl.append("&units=metric");    // To get temperature in Celcius units
        sbUrl.append("&APPID="+API_KEY);  // To authenticate

        String finalUrlString = sbUrl.toString();

        try {
            bypassStrictMainThreadPolicy();
            URL website = new URL(finalUrlString);
            //Gettign XML reader from SimpleApiXML factory to parse data
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            xmlInfoHandler = new HandleXMLInfo();
            xr.setContentHandler(xmlInfoHandler);
            xr.parse(new InputSource(website.openStream()));
            PopulateXMLInfo();
        } catch (Exception e) {
            e.printStackTrace();
            humidText.setText(e.toString());
        }

    }

    private void PopulateXMLInfo() {
        String info;
        info = xmlInfoHandler.getCity();
        cityText.setText(info);

        info = xmlInfoHandler.getCountry();
        countryText.setText(info);

        info = xmlInfoHandler.getTemperature();
        temprText.setText(info);

        info = xmlInfoHandler.getWeather();
        weatherText.setText(info);

        info = xmlInfoHandler.getHumidity();
        humidText.setText(info);
    }

    private void bypassStrictMainThreadPolicy() {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public class GetWeatherInfo extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String city = params[0];
            StringBuilder sbUrl = new StringBuilder(baseURL);
            sbUrl.append(city);
            sbUrl.append("&mode=xml");        // To get response in XML Format
            sbUrl.append("&units=imperial");    // To get temperature in Celcius units
            sbUrl.append("&APPID="+API_KEY);  // To authenticate

            String finalUrlString = sbUrl.toString();

            try {
                URL website = new URL(finalUrlString);
                //Gettign XML reader from SimpleApiXML factory to parse data
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();

                xmlInfoHandler = new HandleXMLInfo();
                xr.setContentHandler(xmlInfoHandler);
                xr.parse(new InputSource(website.openStream()));
                return "OK";
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("OK")) {
                PopulateXMLInfo();
            } else
                humidText.setText(s);
        }
    }
}
