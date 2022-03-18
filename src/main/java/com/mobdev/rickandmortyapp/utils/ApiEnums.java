package com.mobdev.rickandmortyapp.utils;

/**
 * @author: Anthonny Gueli
 * ENUMS to make easier to access Json Values.
 * */
public enum ApiEnums {

    GET_A_SINGLE_CHARACTER("https://rickandmortyapi.com/api/character/{id}"),
    ID("id"),
    NAME("name"),
    STATUS("status"),
    SPECIES("species"),
    TYPE("type"),
    GENDER("gender"),
    ORIGIN("origin"),
    LOCATION("location"),
    IMAGE("image"),
    EPISODE("episode"),
    URL("url"),
    DIMENSION("dimension"),
    RESIDENTS("residents"),
    CREATED("created");


    private String value;

    ApiEnums(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


}
