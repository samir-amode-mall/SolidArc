package fr.ensicaen.ecole.genielogiciel.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void should_get_nickname_when_set_nickname() {
        Player loginModel = new Player();
        final String testString = "Toto";
        loginModel.setNickname(testString);
        assertEquals(testString, loginModel.getNickname());
    }
}