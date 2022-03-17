package com.mobdev.rickandmortyapp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobdev.rickandmortyapp.models.ShowCharacter;
import com.mobdev.rickandmortyapp.service.ShowCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping(path = "api/")
public class RestClient {

    private static final String GET_A_SINGLE_CHARACTER =
            "https://rickandmortyapi.com/api/character/{id}";

    private static String GET_A_SINGLE_LOCATION = "";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String SPECIES = "species";
    private static final String TYPE = "type";
    private static final String GENDER = "gender";
    private static final String ORIGIN = "origin";
    private static final String LOCATION = "location";
    private static final String IMAGE = "image";
    private static final String EPISODE = "episode";
    private static final String URL = "url";
    private static final String CREATED = "created";


    @Autowired
    ShowCharacterService showCharacterService;

    /*
    * Home page.
    * */
    @RequestMapping
    public String ShowCharactersWelcome(){
        return "Bienvenido a la API de la API de personajes de Rick and Morty";
    }


    /*
    * POST method to get Characters from external API.
    * */
    @PostMapping(path = "mobdev/characterLocation/")
    public ShowCharacter getShowCharacter(@RequestBody Map<String, Integer> id) {

        RestTemplate restTemplate = new RestTemplate();
        ShowCharacter finalResponse = new ShowCharacter();
        JsonNode jsonNode = restTemplate.getForObject(
                GET_A_SINGLE_CHARACTER, JsonNode.class,id);

        //jsonNode already has the endpoint to get the location in his response.
        GET_A_SINGLE_LOCATION = jsonNode.get(LOCATION).get(URL).textValue();

        finalResponse.setId(Integer.parseInt(jsonNode.get(ID).toString()));
        finalResponse.setName(jsonNode.get(NAME).textValue());
        finalResponse.setStatus(jsonNode.get(STATUS).textValue());
        finalResponse.setSpecies(jsonNode.get(SPECIES).textValue());
        finalResponse.setType(jsonNode.get(TYPE).textValue());
        finalResponse.setOrigin(jsonNode.get(ORIGIN).toString());
        finalResponse.setOriginasblob(jsonNode.get(ORIGIN).toString()
                .getBytes(StandardCharsets.UTF_8));
        finalResponse.setEpisode_count(jsonNode.get(EPISODE).size());

        /* Unnecessary mapping are commented in case they're needed in the future.
        jsonNode.get(GENDER).textValue();
        jsonNode.get(IMAGE);
        jsonNode.get(URL);
        jsonNode.get(CREATED);*/


        showCharacterService.saveCharacter(finalResponse);
        HELLO

        return finalResponse;
    }

}
