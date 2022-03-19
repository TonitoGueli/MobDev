package com.mobdev.rickandmortyapp.controller;

import com.mobdev.rickandmortyapp.dto.CharacterLocationRequestDTO;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.service.CharacterLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.mobdev.rickandmortyapp.utils.ApiEnums.ID;

/**
 * @author: Anthonny Gueli
 * Internal APi Controller
 */
@RestController
@RequestMapping(path = "api/")
public class CharacterLocationController {

    @Autowired
    CharacterLocationService characterLocationService;

    /**
     * Home page just for the memes.
     */
    @RequestMapping
    public String ShowCharactersWelcome() {
        return "Bienvenido a la API de la API de personajes de Rick and Morty";
    }

    /**
     * POST method to generate final response
     */
    @PostMapping(path = "mobdev/characterLocation/")
    public ControllerResponseDTO getShowCharacter(
            @RequestBody CharacterLocationRequestDTO characterLocationRequestDTO) throws IOException {

        return characterLocationService.generateFinalResponse(
                characterLocationRequestDTO.getRequestMap().get(String.valueOf(ID)));
    }

}
