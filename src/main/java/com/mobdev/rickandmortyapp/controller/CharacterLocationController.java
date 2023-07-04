package com.mobdev.rickandmortyapp.controller;

import com.mobdev.rickandmortyapp.dto.CharacterLocationRequestDTO;
import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.service.CharacterLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CharacterLocationService characterLocationService;

    /**
     * Home page just for the memes.
     */
    @GetMapping("hello")
    public String ShowCharactersWelcome() {
        return "Bienvenido a la API de la API de personajes de Rick and Morty";
    }

    /**
     * POST method to generate final response
     */
    @PostMapping(path = "mobdev/characterLocation/")
    public ControllerResponseDTO getShowCharacter(
            @RequestBody CharacterLocationRequestDTO characterLocationRequestDTO) {

        return characterLocationService.generateFinalResponse(
                characterLocationRequestDTO.getRequestMap().get(String.valueOf(ID)));
    }

}
