package com.mobdev.rickandmortyapp.controller;

import com.mobdev.rickandmortyapp.service.CharacterLocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(CharacterLocationController.class)
public class CharacterLocationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterLocationService characterLocationService;

    @Test
    public void testBasicResponse() throws Exception {

        final String respuestaEsperada = "Bienvenido a la API de la API de personajes de Rick and Morty";
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(respuestaEsperada));
    }
}