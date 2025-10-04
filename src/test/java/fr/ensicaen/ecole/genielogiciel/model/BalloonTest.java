package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BalloonTest {
    @Test
    void testCalculateSCore(){
        double[] wind = {0, 0, 0};
        Balloon balloon = new Balloon(1, 0, 0, 10);
        Bow bow = new Bow();
        Arrow arrow = new Arrow(0, 0, 100, wind);
        assertEquals(10, balloon.calculateScore(arrow,bow.getBowX(),bow.getBowY()));
    }
}
