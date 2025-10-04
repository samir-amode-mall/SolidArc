package fr.ensicaen.ecole.genielogiciel.model;

public class Arrow{

    private double _angleX;
    private double _angleY;
    private double _propulsionForce;
    private double[] _speedWindVector;
    private double _weight = 5;
    private double _xFinal;
    private double _yFinal;

    public Arrow(double angleX, double angleY, double propulsionForce, double[] speedWindVector) {
        _angleX = angleX;
        _angleY = angleY;
        _propulsionForce = propulsionForce;
        _speedWindVector = speedWindVector;
    }

    public double getXFinal(){
        return _xFinal;
    }
    public double getYFinal(){
        return _yFinal;
    }

    @Override
    public String toString(){
        return "La flèche est en (" + _xFinal + " " + _yFinal + ")";
    }

    public void calculateCoords(double xBow, double yBow, double distanceTarget){
        // double normeX = 325;
        // double normeY = 475;
        // double angleOmega = (_angleX - xBow)/normeX;
        // double angleAlpha = (yBow - _angleY)/normeY;

        // double timeWhenOnTarget = Math.sqrt( (2*_weight*distanceTarget) / (_propulsionForce * Math.cos(angleAlpha) * Math.cos(angleOmega)) );

        // _xFinal = _angleX + ( _propulsionForce * Math.cos(angleAlpha) * Math.sin(angleOmega) ) * Math.pow(timeWhenOnTarget,2) / ( 2*_weight );
        // _yFinal = _angleY - ( _propulsionForce * Math.sin(angleAlpha) - _weight*9.81) * Math.pow(timeWhenOnTarget,2) / ( 2*_weight );

        _xFinal = _angleX + (_angleX - xBow)*0.1;
        _yFinal = _angleY + (yBow - _angleY)*0.25;
    }

}