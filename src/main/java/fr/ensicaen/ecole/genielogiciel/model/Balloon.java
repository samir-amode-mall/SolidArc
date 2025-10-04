package fr.ensicaen.ecole.genielogiciel.model;

public class Balloon extends Target {

    private boolean _isTouched = false;

    public Balloon(){
        super();
    }

    public Balloon(double distanceTarget, double xTarget, double yTarget, double radius){
        super(distanceTarget, xTarget, yTarget, radius);
    }

    public boolean get_isTouched() {
        return _isTouched;
    }

    int calculateScore(Arrow arrow, double xBow, double yBow){
        int score = 0;
        arrow.calculateCoords(xBow, yBow, _distanceTarget);
        double arrow_x = arrow.getXFinal();
        double arrow_y = arrow.getYFinal();
        double distanceCenter = Math.sqrt( Math.pow(arrow_x - (_xTarget+50), 2) + Math.pow(arrow_y - (_yTarget+50),2) );
        if ((distanceCenter <= _radius) && !_isTouched) {
            _isTouched = true;
            score = 10;
        }
        return score;
    }
}

