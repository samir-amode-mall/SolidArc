package fr.ensicaen.ecole.genielogiciel.model;

public class Score {
    private int _score;
    public Score() {
        _score = 0;
    }
    public Score(int score) {
        _score = score;
    }
    public int getScore() {
        return _score;
    }
    public void setScore(int score) {
        _score = score;
    }
    public void addScore(int score) {
        _score += score;
    }

    public String toString(){
        return "Le score total est : " + _score;
    }
}
