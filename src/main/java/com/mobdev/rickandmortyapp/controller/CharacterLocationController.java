package com.mobdev.rickandmortyapp.controller;

import com.mobdev.rickandmortyapp.dto.ControllerResponseDTO;
import com.mobdev.rickandmortyapp.service.ShowCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mobdev.rickandmortyapp.utils.ApiEnums.ID;

/**
* @author: Anthonny Gueli
* Internal APi Controller
* */
@RestController
@RequestMapping(path = "api/")
public class CharacterLocationController {

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
    * POST method to generate final response
    * */
    @PostMapping(path = "mobdev/characterLocation/")
    public ControllerResponseDTO getShowCharacter(@RequestBody Map<String, Integer> id) {
        return showCharacterService.generateFinalResponse(id.get(String.valueOf(ID)));
    }

}
