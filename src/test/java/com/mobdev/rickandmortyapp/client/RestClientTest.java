package com.mobdev.rickandmortyapp.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestClientTest {

    private RestClient restClient;

    @BeforeEach
    public void setUp() {
        restClient = new RestClient();
    }

    @Test
    void getShowCharacter()   {
        restClient.getShowCharacterAsJson(1);
        restClient.getLocationFromCharacter();
    }

    @Test
    void getShowCharacterException()   {
        restClient.getShowCharacterAsJson(105);
        restClient.getLocationFromCharacter();
    }
}