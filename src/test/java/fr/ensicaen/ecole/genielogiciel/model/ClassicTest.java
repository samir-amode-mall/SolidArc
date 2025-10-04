package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassicTest {
    @Test
    void testCalculateScore() {
        double[] wind = {0, 0, 0};
        Classic classic = new Classic(1, 0, 0, 10);
        Bow bow = new Bow();
        Arrow arrow = new Arrow(0, 0, 100, wind);
        assertEquals(10, classic.calculateScore(arrow,bow.getBowX(),bow.getBowY()));
    }
}
