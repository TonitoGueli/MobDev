package com.mobdev.rickandmortyapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobdev.rickandmortyapp.client.RestClient;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import com.mobdev.rickandmortyapp.repository.LocationRepository;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
* @author: Anthonny Gueli
* Service layer which contains business logic.
* */
@Service
public class CharacterLocationService {

    private final ShowCharacterRepository showCharacterRepository;
    private final LocationRepository locationRepository;
    private final RestClient restClient = new RestClient();

    @Autowired
    public CharacterLocationService(ShowCharacterRepository showCharacterRepository, LocationRepository locationRepository) {
        this.showCharacterRepository = showCharacterRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * This method persist both responses coming from both APis and also builds the final response
     * based on the ControllerResponseDTO class.
     * */
    public ControllerResponseDTO generateFinalResponse (Integer id) throws IOException {

        /**
         * Character object persistence
         * */
        ShowCharacterResponseDTO characterData = restClient.getShowCharacter(id);
        saveCharacterTransaction(characterData);

        /**
         * Location object persistence
         * */
        LocationResponseDTO locationData = restClient.getLocationFromCharacter();
        saveLocationTransaction(locationData);

        /**
         * LocationList string split by comma regex value
         * Decoded bytes[]
         * Decoded Base64
         * */
        String residentsFromLocationData = locationData.getResidents();
        byte[] decodedBytesResidentsFromLocationData = Base64.getDecoder().decode(residentsFromLocationData);
        String decodedResidentsFromLocationData = new String(decodedBytesResidentsFromLocationData);
        decodedResidentsFromLocationData.replace("'","");
        ArrayList<String> arrayResidents = new ArrayList<>();
        List<String> existingData = new ArrayList<>(Arrays.asList(decodedResidentsFromLocationData.split(",")));
        for (int i = 0; i < existingData.size(); i++) {
            arrayResidents.add(existingData.get(i).replaceAll("\"|(^\\[|\\]$)", ""));
        }



        /**
         * Origin node building for final response
         * It will be injected latter.
         * */
        ControllerResponseDTO.Origin originObject = ControllerResponseDTO.Origin
                .builder()
                .name(characterData.getOrigin())
                .url(locationData.getUrl())
                .dimension(locationData.getDimension())
                .residents(arrayResidents)
                .build();

        /**
         * Final response building.
         * We inject the Origin object here.
        * */
        ControllerResponseDTO finalResponseObject = ControllerResponseDTO
                .builder()
                .id(characterData.getId())
                .name(characterData.getName())
                .status(characterData.getStatus())
                .species(characterData.getSpecies())
                .type(characterData.getType())
                .episode_count(characterData.getEpisode_count())
                .origin(originObject)
                .build();

        return finalResponseObject;
    }

    /**
     * Method to persist in the Characters table.
     * */
    public void saveCharacterTransaction(ShowCharacterResponseDTO showCharacter){
        showCharacterRepository.save(showCharacter);
    }

    /**
     * Method to persist in the Location table.
     * */
    public void saveLocationTransaction(LocationResponseDTO locationResponseDTO){
        /**
         * Base64 encryption.
         * */
        String residentsString = locationResponseDTO.getResidents();
        String residentsEncrypted = Base64.getEncoder().
                encodeToString(residentsString.getBytes(StandardCharsets.UTF_8));
        locationResponseDTO.setResidents(residentsEncrypted);

        locationRepository.save(locationResponseDTO);
    }

}
