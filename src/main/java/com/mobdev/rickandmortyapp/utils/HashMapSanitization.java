package com.mobdev.rickandmortyapp.utils;


import java.util.HashMap;

public class HashMapSanitization {
    /**
     * Origin string split and value sanitization
     * Also morphed the String to HashMap for simplicity when making the final response in the builder method.
     * */
    public HashMap<String,String> sanitizeHashMap(String[] stringList){

        HashMap<String, String> originMap = new HashMap<>();
        for (String s : stringList
        ) {
            String[] section = s.split(":", 2);
            originMap.put(section[0].replaceAll("\"|\\{|\\}", "")
                    , section[1].replaceAll("\"|\\{|\\}", ""));
        }
        return originMap;
    }
}
