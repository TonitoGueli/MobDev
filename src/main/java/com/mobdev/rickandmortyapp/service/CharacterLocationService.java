package com.mobdev.rickandmortyapp.service;

import com.mobdev.rickandmortyapp.client.RestClient;
import com.mobdev.rickandmortyapp.model.CharactersApiResponse;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import com.mobdev.rickandmortyapp.repository.LocationRepository;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import com.mobdev.rickandmortyapp.utils.ArrayListSanitization;
import com.mobdev.rickandmortyapp.utils.HashMapSanitization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.mobdev.rickandmortyapp.utils.ApiEnums.NAME;
import static com.mobdev.rickandmortyapp.utils.ApiEnums.URL;

/**
 * @author: Anthonny Gueli
 * Service layer which contains business logic.
 */
@Service
public class CharacterLocationService {

    private final ShowCharacterRepository showCharacterRepository;
    private final LocationRepository locationRepository;
    private final RestClient restClient = new RestClient();
    private ArrayListSanitization arrayListSanitization = new ArrayListSanitization();
    private HashMapSanitization hashMapSanitization = new HashMapSanitization();

    @Autowired
    public CharacterLocationService(ShowCharacterRepository showCharacterRepository, LocationRepository locationRepository) {
        this.showCharacterRepository = showCharacterRepository;
        this.locationRepository = locationRepository;
    }


    /**
     * This method persist both responses coming from both APis and also builds the final response
     * based on the ControllerResponseDTO class.
     */
    public ControllerResponseDTO generateFinalResponse(Integer id) {

        /**
         * Character object persistence
         * */
        ShowCharacterResponseDTO characterData = restClient.getShowCharacterAsJson(id);
        saveCharacterTransaction(characterData);

        /**
         *
         * Getting a ResponseEntity instead of a Json.
         * This doesn't require persistence since the class "CharactersApiResponse" is not a DB entity.
         * */
        ResponseEntity<CharactersApiResponse> characterDataAsEntity = restClient.getShowCharacterAsEntity(id);

        /**
         * Location object persistence
         * */
        LocationResponseDTO locationData = restClient.getLocationFromCharacter();
        saveLocationTransaction(locationData);


        ArrayList<String> arrayResidents =
                new ArrayList<>(arrayListSanitization.sanitizeValues(locationData.getResidents()));

        HashMap<String, String> originMap =
                new HashMap<>(hashMapSanitization.sanitizeHashMap(characterData.getOrigin().split(",")));

        /**
         * Origin node building for final response
         * It will be injected latter.
         * */
        ControllerResponseDTO.Origin originObject = ControllerResponseDTO.Origin
                .builder()
                .name(originMap.get(String.valueOf(NAME)))
                .url(originMap.get(String.valueOf(URL)))
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
     */
    public void saveCharacterTransaction(ShowCharacterResponseDTO showCharacter) {
        showCharacterRepository.save(showCharacter);
    }

    /**
     * Method to persist in the Location table.
     */
    public void saveLocationTransaction(LocationResponseDTO locationResponseDTO) {
        /**
         * Base64 encryption.
         * */
        try {
            String residentsString = locationResponseDTO.getResidents();
            String residentsEncrypted = Base64.getEncoder().
                    encodeToString(residentsString.getBytes(StandardCharsets.UTF_8));
            locationResponseDTO.setResidents(residentsEncrypted);
        } catch (Exception e) {

        }


        locationRepository.save(locationResponseDTO);
    }

}
