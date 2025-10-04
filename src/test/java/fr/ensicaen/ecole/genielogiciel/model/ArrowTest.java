package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrowTest {

    @Test
    void testArrowCalculatesFinalCoordinates() {
        double[] wind = {0,0};
        Arrow arrow = new Arrow(45, 30, 100, wind);
        arrow.calculateCoords(325, 720, 100);

        double finalX = arrow.getXFinal();
        double finalY = arrow.getYFinal();

        assertNotEquals(45, finalX, "La coordonnée X finale ne devrait pas être égale à l'angle initial.");
        assertNotEquals(30, finalY, "La coordonnée Y finale ne devrait pas être égale à l'angle initial.");
    }

    @Test
    void testArrowHitsTarget() {
        double [] wind = {0,0};
        Arrow arrow = new Arrow(0, 0, 100,wind);
        arrow.calculateCoords(0, 0, 0);

        assertEquals(0, arrow.getXFinal(), "La flèche aurait dû atteindre la coordonnée X de la cible.");
        assertEquals(0, arrow.getYFinal(), "La flèche aurait dû atteindre la coordonnée Y de la cible.");
    }

    @Test
    void testNegativeCoordinates() {
        double [] wind = {0,0};
        Arrow arrow = new Arrow(-45, -30, 100,wind);
        arrow.calculateCoords(-50, -50, 100);

        double finalX = arrow.getXFinal();
        double finalY = arrow.getYFinal();

        assertTrue(finalX < 0, "La flèche aurait dû se déplacer vers une coordonnée X négative.");
        assertTrue(finalY < 0, "La flèche aurait dû se déplacer vers une coordonnée Y négative.");
    }
}
