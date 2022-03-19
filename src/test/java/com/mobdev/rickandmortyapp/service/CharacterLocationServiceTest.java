package com.mobdev.rickandmortyapp.service;

import com.mobdev.rickandmortyapp.client.RestClient;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import com.mobdev.rickandmortyapp.repository.LocationRepository;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CharacterLocationServiceTest {

    CharacterLocationService characterLocationService;
    ShowCharacterRepository showCharacterRepository;
    LocationRepository locationRepository;
    RestClient restClient;
    ShowCharacterResponseDTO characterResponseDTO;
    LocationResponseDTO locationResponseDTO;

    @BeforeEach
    public void setUp() {
        characterResponseDTO = Mockito.mock(ShowCharacterResponseDTO.class);
        showCharacterRepository = Mockito.mock(ShowCharacterRepository.class);
        locationRepository = Mockito.mock(LocationRepository.class);
        characterLocationService = new CharacterLocationService(showCharacterRepository, locationRepository);
        restClient = Mockito.mock(RestClient.class);
        locationResponseDTO = Mockito.mock(LocationResponseDTO.class);

    }

    @Test
    public void generateFinalResponse() throws IOException {

        characterLocationService.generateFinalResponse(1);

    }

    @Test
    public void generateFinalResponseException() throws IOException {

        characterLocationService.generateFinalResponse(105);

    }

}