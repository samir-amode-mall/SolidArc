package fr.ensicaen.ecole.genielogiciel.model;


import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Wind {
    private double _speed;
    private String _direction;


    public void setSpeed(double speed) {
        _speed = speed;
    }

    public void setDirection(String direction) {
        _direction=direction;
    }

    public double[] getWind() {
        getWindConditions();
        return this.getVelocity();
    }

    public double getSpeed() {
        return _speed;
    }

    public String getDirection() {
        return _direction;
    }

    private static final String METEO_URL = "https://www.prevision-meteo.ch/services/json/lat=49.214lng=-0.367";

    private void generateRandomWindConditions(){
        Random randS = new Random();
        _speed = 5 + (30 - 5) * randS.nextDouble(); //speed between 5km/h and 30km/h

        String[] Directions = {"E", "N", "W", "S", "NE", "SE", "NW", "SW"};
        Random randD = new Random();
        int randomIndex = randD.nextInt(Directions.length);
        _direction = Directions[randomIndex];
    }

    public void getWindConditions(){
        URL url = null;
        try {
            url = new URL(METEO_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            JsonObject jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();

            _speed = jsonResponse.getAsJsonObject("current_condition").get("wnd_spd").getAsDouble();
            _direction = jsonResponse.getAsJsonObject("current_condition").get("wnd_dir").getAsString();
        }
        catch (Exception e) {
            System.err.println("Connection failed, Random conditions generated");
            generateRandomWindConditions();
        }

    }

    public double[] getVelocity() {
        switch (_direction) {
            case "E":
                return new double[]{-_speed,0};
            case "N":
                return new double[]{0,_speed};
            case "W":
                return new double[]{_speed,0};
            case "S":
                return new double[]{0,-_speed};
            case "NE":
                return new double[]{_speed * Math.cos(3 * Math.PI/4),_speed * Math.sin(3 * Math.PI/4)};
            case "SE":
                return new double[]{_speed * Math.cos(-3 * Math.PI/4),_speed * Math.sin(-3 * Math.PI/4)};
            case "NW":
                return new double[]{_speed * Math.cos(Math.PI/4),_speed * Math.sin(Math.PI/4)};
            case "SW":
                return new double[]{_speed * Math.cos(-Math.PI/4),_speed * Math.sin(-Math.PI/4)};
            default:
                return new double[]{0,0};
        }
    }

}
