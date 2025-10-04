package fr.ensicaen.ecole.genielogiciel.model;

import java.util.ArrayList;


public class Bow{

    private double _propulsionForce;
    private double _angleX;
    private double _angleY;
    private double _xBow;
    private double _yBow;
    private int _nbArrow;
    private Arrow _arrow;

    public Bow() {
        _propulsionForce = 0.0;
        _angleX = 0.0;
        _angleY = 0.0;
        _xBow = 325;
        _yBow = 720;
        _nbArrow = 10;
    }
    public double getForce(){
        return _propulsionForce;
    }
    public double getAngleX() {return _angleX;}
    public double getAngleY() {return _angleY;}
    public double getBowX() {return _xBow;}
    public double getBowY() {return _yBow;}
    public int getNbArrow(){
        return _nbArrow;
    }

    @Override
    public String toString(){
        return "L'arc vise en (" + _angleX + " " + _angleY + ") avec une propulsion de : " + _propulsionForce;
    }


    public int shootArrow(Target target, double[] speedWindVector){
        int score = 0;
        if (_nbArrow-- > 0){
            _arrow = new Arrow(_angleX,_angleY,_propulsionForce,speedWindVector);
            score = target.calculateScore(_arrow,_xBow,_yBow);
            System.out.println(_arrow.toString());
        }
        return score;
    }

    public int shootArrow(ArrayList<Target> targets, double[] speedWindVector){
        int score = 0;
        if (_nbArrow-- > 0){
            _arrow = new Arrow(_angleX,_angleY,_propulsionForce, speedWindVector);
            for (Target target : targets) {
                score += target.calculateScore(_arrow,_xBow,_yBow);
            }
        }
        return score;
    }

    public void move(double x,double y) {
        _angleX = x + _angleX;
        _angleY = y + _angleY;
    }

    public void chooseDirection(double x, double y){
        _angleX = x;
        _angleY = y;
    }


    public void addForce(float force){
        if (_propulsionForce<100) {
            _propulsionForce += force;
        }
        if (_propulsionForce>100){
            _propulsionForce=100;
        }
    }

    public double getArrowX(){
        return _arrow.getXFinal();
    }

    public double getArrowY(){
        return _arrow.getYFinal();
    }

}