package fr.ensicaen.ecole.genielogiciel.model;


public abstract class Target {

    protected double _xTarget;
    protected double _yTarget;
    protected double _distanceTarget;
    protected double _radius;
    abstract int calculateScore(Arrow arrow, double xBow, double yBow);

    public Target(){
        _distanceTarget = 100;
        _xTarget = 325;
        _yTarget = 475;
        _radius = 50;
    }

    public Target(double distanceTarget, double xTarget, double yTarget, double radius){
        _distanceTarget = distanceTarget;
        _xTarget = xTarget;
        _yTarget = yTarget;
        _radius = radius;
    }

    public double get_xTarget() {
        return _xTarget;
    }

    public double get_yTarget() {
        return _yTarget;
    }
}
