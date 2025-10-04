package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BowTest {


    @Test
    void testShootArrow() {
        Bow bow = new Bow();
        double[] wind = {0,0};
        Target mockTarget = mock(Target.class);
        when(mockTarget.calculateScore(any(Arrow.class),bow.getBowX(),bow.getBowY())).thenReturn(50);

        int score = bow.shootArrow(mockTarget,wind);

        assertEquals(50, score);
        assertEquals(24, bow.getNbArrow());
        verify(mockTarget, times(1)).calculateScore(any(Arrow.class),bow.getBowX(),bow.getBowY());
    }

    @Test
    void testShootArrow_NoArrowsLeft() {
        Bow bow = new Bow();
        double[] wind = {0,0};
        Target mockTarget = mock(Target.class);
        bow.shootArrow(mockTarget,wind); // tire 25 flèches
        for (int i = 0; i < 24; i++) {
            bow.shootArrow(mockTarget,wind);
        }
        int score = bow.shootArrow(mockTarget,wind);

        assertEquals(0, score);
    }

    @Test
    void testMove() {
        Bow bow = new Bow();
        bow.move(10.0, 20.0);
        assertEquals(10.0, bow.getAngleX());
        assertEquals(20.0, bow.getAngleY());

        bow.move(-5.0, -10.0);
        assertEquals(5.0, bow.getAngleX());
        assertEquals(10.0, bow.getAngleY());
    }

    @Test
    void testChooseDirection() {
        Bow bow = new Bow();
        bow.chooseDirection(45.0, 30.0);
        assertEquals(45.0, bow.getAngleX());
        assertEquals(30.0, bow.getAngleY());
    }

    @Test
    void testAddForce() {
        Bow bow = new Bow();
        bow.addForce(50.0f);
        assertEquals(50.0, bow.getForce());

        bow.addForce(60.0f);
        assertEquals(100.0, bow.getForce());
    }

    @Test
    void testGetArrowPositionAfterShoot() {
        Bow bow = new Bow();
        double[] wind = {0,0};
        Target mockTarget = mock(Target.class);
        when(mockTarget.calculateScore(any(Arrow.class),bow.getBowX(),bow.getBowY())).thenReturn(50);

        bow.shootArrow(mockTarget,wind);
        assertEquals(bow.getArrowX(), 0.0);
        assertEquals(bow.getArrowY(), 0.0);
    }
}
