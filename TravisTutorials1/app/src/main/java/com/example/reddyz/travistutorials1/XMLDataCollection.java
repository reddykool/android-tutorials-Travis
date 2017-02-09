package com.example.reddyz.travistutorials1;

/**
 * Created by Reddyz on 31-10-2016.
 */

public class XMLDataCollection {
    String city, country, temperature, weather, humidity;

    public void setCity(String c) {
        city = c;
    }

    public void setTemperature(String t) {
        temperature = t;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity + "% Humidity";
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getWeather() {
        return weather;
    }

    public String getHumidity() {
        return humidity;
    }

}
