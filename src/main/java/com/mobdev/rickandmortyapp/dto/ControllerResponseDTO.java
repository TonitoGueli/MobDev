package com.mobdev.rickandmortyapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ControllerResponseDTO implements Serializable {

    public int id;
    public String name;
    public String status;
    public String species;
    public String type;
    public int episode_count;
    public Origin origin;

    public class Origin{
        public String name;
        public String url;
        public String dimension;
        public ArrayList<String> residents;
    }


}
