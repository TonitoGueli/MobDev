package com.mobdev.rickandmortyapp.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author: Anthonny Gueli
 * Request DTO Class
 */
@Data
public class CharacterLocationRequestDTO {
    private Map<String, Integer> requestMap;
}
