package com.example.reddyz.travistutorials1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Reddyz on 31-10-2016.
 */

public class HandleXMLInfo extends DefaultHandler{

    XMLDataCollection infoCollected = new XMLDataCollection();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equals("city")) {
            String city = attributes.getValue("name");
            infoCollected.setCity(city);
        } else if (localName.equals("country")) {
            String country = attributes.getValue(0);
            infoCollected.setCountry(country);
        } else if (localName.equals("temperature")) {
            String tempr = attributes.getValue("value");
            infoCollected.setTemperature(tempr);
        } else if(localName.equals("humidity")) {
            String humidity = attributes.getValue("value");
            infoCollected.setHumidity(humidity);
        } else if(localName.equals("weather")) {
            String weather = attributes.getValue("value");
            infoCollected.setWeather(weather);
        }
    }



    public String getCity() {
        return infoCollected.getCity();
    }

    public String getCountry() {
        return infoCollected.getCountry();
    }

    public String getTemperature() {
        return infoCollected.getTemperature();
    }

    public String getWeather() {
        return infoCollected.getWeather();
    }

    public String getHumidity() {
        return infoCollected.getHumidity();
    }
}
