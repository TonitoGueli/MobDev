package com.mobdev.rickandmortyapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharactersApiResponse {

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private int episode_count;
    private Object origin;
    private String lastName;
}
