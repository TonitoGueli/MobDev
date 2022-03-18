package com.mobdev.rickandmortyapp.service;

import com.mobdev.rickandmortyapp.client.RestClient;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* Service layer which contains business logic.
* */
@Service
public class ShowCharacterService {

    private final ShowCharacterRepository showCharacterRepository;
    private final RestClient restClient;


    @Autowired
    public ShowCharacterService(ShowCharacterRepository showCharacterRepository, RestClient restClient) {
        this.showCharacterRepository = showCharacterRepository;
        this.restClient = restClient;
    }

    public void saveCharacter(ShowCharacterResponseDTO showCharacter){
        showCharacterRepository.save(showCharacter);
    }

    public ControllerResponseDTO generateFinalResponse (String id){
        restClient.getShowCharacter(id);

        return
    }



}
