package com.mobdev.rickandmortyapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
* @author: Anthonny Gueli
* Response DTO Class
* */
@Data
@Builder
public class ControllerResponseDTO implements Serializable{

    public int id;
    public String name;
    public String status;
    public String species;
    public String type;
    public int episode_count;
    public Origin origin;

    @Data
    @Builder
    public static class Origin{
        public String name;
        public String url;
        public String dimension;
        public List<String> residents;
    }


}
