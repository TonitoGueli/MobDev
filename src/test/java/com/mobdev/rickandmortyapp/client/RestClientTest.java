package com.mobdev.rickandmortyapp.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestClientTest {

    private RestClient restClient;

    @BeforeEach
    public void setUp() {
        restClient = new RestClient();
    }

    @Test
    void getShowCharacter() throws JsonProcessingException {
        restClient.getShowCharacter(1);
        restClient.getLocationFromCharacter();
    }

    @Test
    void getShowCharacterException() throws JsonProcessingException {
        restClient.getShowCharacter(105);
        restClient.getLocationFromCharacter();
    }
}