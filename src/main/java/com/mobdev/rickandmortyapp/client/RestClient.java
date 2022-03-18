package com.mobdev.rickandmortyapp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

import static com.mobdev.rickandmortyapp.utils.ApiEnums.*;

public class RestClient {

    private String getLocationFromCharacter = "";

    public ShowCharacterResponseDTO getShowCharacter (String id){
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(
                GET_A_SINGLE_CHARACTER.toString(), JsonNode.class,id);

        ShowCharacterResponseDTO characterResponseDTO = ShowCharacterResponseDTO.builder()
                .id(Integer.parseInt(jsonNode.get(String.valueOf(ID)).toString()))
                .name(jsonNode.get(String.valueOf(NAME)).toString())
                .status(jsonNode.get(String.valueOf(STATUS)).toString())
                .species(jsonNode.get(String.valueOf(SPECIES)).toString())
                .type(jsonNode.get(String.valueOf(TYPE)).toString())
                .episode_count(jsonNode.get(String.valueOf(EPISODE)).size())
                .originAsBlob(jsonNode.get(String.valueOf(ORIGIN)).toString()
                        .getBytes(StandardCharsets.UTF_8))
                .origin(jsonNode.get(String.valueOf(ORIGIN)).toString())
                .build();

        getLocationFromCharacter = jsonNode.get(String.valueOf(LOCATION)).textValue();

        return characterResponseDTO;
    }

    public LocationResponseDTO getLocationFromCharacter (String locationFromCharacterEndpoint){
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(
                getLocationFromCharacter,JsonNode.class);

        LocationResponseDTO locationResponseDTO = LocationResponseDTO.builder()
                .id(Integer.parseInt(jsonNode.get(String.valueOf(ID)).toString()))
                .name(jsonNode.get(String.valueOf(NAME)).toString())
                .url(jsonNode.get(String.valueOf(URL)).toString())
                .dimension(jsonNode.get(String.valueOf(DIMENSION)).toString())
                .residentsAsBlob(jsonNode.get(String.valueOf(RESIDENTS)).toString()
                        .getBytes(StandardCharsets.UTF_8))
                .residents(jsonNode.get(String.valueOf(RESIDENTS)).textValue())
                .build();

        return locationResponseDTO;
    }

}
