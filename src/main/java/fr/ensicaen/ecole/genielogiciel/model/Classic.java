package fr.ensicaen.ecole.genielogiciel.model;

public class Classic extends Target {

    public Classic(){
        super();
    }

    public Classic(double distanceTarget, double xTarget, double yTarget, double radius){
        super(distanceTarget, xTarget, yTarget, radius);
    }

    int calculateScore(Arrow arrow, double xBow, double yBow){
        arrow.calculateCoords(xBow, yBow, _distanceTarget);
        double arrow_x = arrow.getXFinal();
        double arrow_y = arrow.getYFinal();
        double distanceCenter = Math.sqrt( Math.pow(arrow_x - _xTarget, 2) + Math.pow(arrow_y - _yTarget,2) );

        return distanceCenter <= _radius*0.1 ? 10 :
               distanceCenter <= _radius*0.2 ? 9 :
               distanceCenter <= _radius*0.3 ? 8 :
               distanceCenter <= _radius*0.4 ? 7 :
               distanceCenter <= _radius*0.5 ? 6 :
               distanceCenter <= _radius*0.6 ? 5 :
               distanceCenter <= _radius*0.7 ? 4 :
               distanceCenter <= _radius*0.8 ? 3 :
               distanceCenter <= _radius*0.9 ? 2 :
               distanceCenter <= _radius ? 1 : 0;
    }
}
