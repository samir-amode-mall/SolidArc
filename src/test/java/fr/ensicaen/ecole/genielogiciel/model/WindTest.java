package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindTest {
    private Wind wind;

    @BeforeEach
    public void setUp() {
        wind = new Wind();
    }

    @Test
    public void testGenerateRandomWindConditions() throws Exception {
        // Utilisation de la réflexion pour accéder à la méthode privée
        Method method = Wind.class.getDeclaredMethod("generateRandomWindConditions");
        method.setAccessible(true);

        method.invoke(wind);

        // Vérification que la vitesse est dans l'intervalle [5, 30]
        assertTrue(wind.getWind()[0] >= 5 && wind.getWind()[0] <= 30);

        // Vérification que la direction est l'une des directions possibles
        String[] validDirections = {"E", "N", "W", "S", "NE", "SE", "NW", "SW"};
        assertTrue(Arrays.asList(validDirections).contains(wind.getDirection()));
    }

    @Test
    public void testGetVelocityNorthDirection() {
        wind.setSpeed(10);
        wind.setDirection("N");
        double[] velocity = wind.getVelocity();
        assertArrayEquals(new double[]{0, 10}, velocity);

    }

    @Test
    public void testGetVelocityEastDirection(){
        wind.setSpeed(10);
        wind.setDirection("E");
        double[] velocity = wind.getVelocity();
        assertArrayEquals(new double[]{-10, 0}, velocity);
    }

    @Test
    public void testGetVelocityNorthEastDirection(){
        wind.setSpeed(10);
        wind.setDirection("NE");
        double[] velocity = wind.getVelocity();
        assertArrayEquals(new double[]{10 * Math.cos(3* Math.PI / 4), 10 * Math.sin(3*Math.PI / 4)}, velocity);
    }
}
