package com.mobdev.rickandmortyapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CharacterLocationControllerTest {


    private CharacterLocationController characterLocationController;

    @BeforeEach
    public void setUp() {
        characterLocationController = new CharacterLocationController();

    }

    @Test()
    public void testBasicResponse() {

        Assertions.assertEquals(
                "Bienvenido a la API de la API de personajes de Rick and Morty"
                , characterLocationController.ShowCharactersWelcome());
    }
}