package com.mobdev.rickandmortyapp.service;

import com.mobdev.rickandmortyapp.models.ShowCharacter;
import com.mobdev.rickandmortyapp.repository.ShowCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* Service layer which contains business logic.
* */
@Service
public class ShowCharacterService {

    private final ShowCharacterRepository showCharacterRepository;


    @Autowired
    public ShowCharacterService(ShowCharacterRepository showCharacterRepository) {
        this.showCharacterRepository = showCharacterRepository;
    }

    public void saveCharacter(ShowCharacter showCharacter){
        showCharacterRepository.save(showCharacter);
    }

}
