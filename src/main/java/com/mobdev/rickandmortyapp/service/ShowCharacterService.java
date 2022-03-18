package com.mobdev.rickandmortyapp.service;

import com.mobdev.rickandmortyapp.client.RestClient;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import com.mobdev.rickandmortyapp.repository.LocationRepository;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author: Anthonny Gueli
* Service layer which contains business logic.
* */
@Service
public class ShowCharacterService {

    private final ShowCharacterRepository showCharacterRepository;
    private final LocationRepository locationRepository;
    private final RestClient restClient = new RestClient();


    @Autowired
    public ShowCharacterService(ShowCharacterRepository showCharacterRepository, LocationRepository locationRepository) {
        this.showCharacterRepository = showCharacterRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * This method persist both responses coming from both APis and also builds the final response
     * based on the ControllerResponseDTO class.
     * */
    public ControllerResponseDTO generateFinalResponse (Integer id){
        saveCharacterTransaction(restClient.getShowCharacter(id));
        saveLocationTransaction(restClient.getLocationFromCharacter());

        return new ControllerResponseDTO();
    }

    /**
     * Method to persis in the Characters table.
     * */
    public void saveCharacterTransaction(ShowCharacterResponseDTO showCharacter){
        showCharacterRepository.save(showCharacter);
    }

    /**
     * Method to persis in the Location table.
     * */
    public void saveLocationTransaction(LocationResponseDTO locationResponseDTO){
        locationRepository.save(locationResponseDTO);
    }



}
