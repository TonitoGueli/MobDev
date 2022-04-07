package com.mobdev.rickandmortyapp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobdev.rickandmortyapp.model.CharactersApiResponse;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.mobdev.rickandmortyapp.utils.ApiEnums.*;

/**
 * @author: Anthonny Gueli
 * Calls to external APis
 */
public class RestClient {

    private String getLocationFromCharacter = "";

    /**
     * Gets API Response as JSON.
     * Makes a call to characterApi
     * Needs an int id as input.
     * Based on the APi response, it saves the Character's Location URL.
     * This method sends the Location URL to the method getLocationFromCharacter.
     * Returns a ShowCharacterResponseDTO object.
     */
    public ShowCharacterResponseDTO getShowCharacterAsJson(Integer id)  {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject( // getForEntity
                GET_A_SINGLE_CHARACTER.toString(), JsonNode.class, id);

        ShowCharacterResponseDTO characterResponseDTO = ShowCharacterResponseDTO
                .builder()
                .id(Integer.parseInt(jsonNode.get(String.valueOf(ID))
                        .toString()))
                .name(jsonNode.get(String.valueOf(NAME)
                        .replaceAll("'", "")).textValue())
                .status(jsonNode.get(String.valueOf(STATUS)
                        .replaceAll("'", "")).textValue())
                .species(jsonNode.get(String.valueOf(SPECIES)
                        .replaceAll("'", "")).textValue())
                .type(jsonNode.get(String.valueOf(TYPE)
                        .replaceAll("'", "")).textValue())
                .episode_count(jsonNode.get(String.valueOf(EPISODE))
                        .size())
                .origin(jsonNode.get(String.valueOf(ORIGIN)
                        .replaceAll("'", "")).toString())
                .build();

        getLocationFromCharacter = jsonNode.get(String.valueOf(ORIGIN))
                .get(String.valueOf(URL).replaceAll("'", "")).textValue();


        return characterResponseDTO;
    }

    /**
     * Gets API Response as an Entity.
     * Makes a call to characterApi
     * Needs an int id as input.
     * Based on the APi response, it saves the Character's Location URL.
     * This method sends the Location URL to the method getLocationFromCharacter.
     * Returns a ShowCharacterResponseDTO object.
     */
    public ResponseEntity<CharactersApiResponse> getShowCharacterAsEntity(Integer id)  {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CharactersApiResponse> showCharacter = restTemplate.getForEntity(
                GET_A_SINGLE_CHARACTER.toString(), CharactersApiResponse.class, id);

        return showCharacter;
    }

    /**
     * This method receives the Character's Location URL from the getShowCharacter method as input.
     * Makes a call to the Location API.
     * Returns a LocationResponseDTO object.
     */
    public LocationResponseDTO getLocationFromCharacter() {
        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode;
        LocationResponseDTO locationResponseDTO;
        try {
            jsonNode = restTemplate.getForObject(
                    getLocationFromCharacter, JsonNode.class);
            locationResponseDTO = LocationResponseDTO
                    .builder()
                    .id(Integer.parseInt(jsonNode.get(String.valueOf(ID)).toString()))
                    .name(jsonNode.get(String.valueOf(NAME)
                            .replaceAll("'", "")).textValue())
                    .url(jsonNode.get(String.valueOf(URL)
                            .replaceAll("'", "")).textValue())
                    .dimension(jsonNode.get(String.valueOf(DIMENSION)
                            .replaceAll("'", "")).textValue())
                    .residents(jsonNode.get(String.valueOf(RESIDENTS)).toString()
                            .replaceAll("'", ""))
                    .build();
        } catch (Exception e) {
            locationResponseDTO = LocationResponseDTO.builder().build();
        }

        return locationResponseDTO;
    }

}
