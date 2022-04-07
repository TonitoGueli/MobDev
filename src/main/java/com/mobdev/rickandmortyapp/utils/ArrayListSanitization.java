package com.mobdev.rickandmortyapp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ArrayListSanitization {

    /**
     * LocationList string split and value sanitization
     * Decoded bytes[]
     * Decoded Base64
     * */
    public ArrayList<String> sanitizeValues(String stringWithTrash){
        ArrayList<String> arrayResidents = new ArrayList<>();
        try {
            byte[] decodedBytesResidentsFromLocationData = Base64.getDecoder().decode(stringWithTrash);
            String decodedResidentsFromLocationData = new String(decodedBytesResidentsFromLocationData);
            decodedResidentsFromLocationData.replace("'", "");
            List<String> existingData = new ArrayList<>(Arrays.asList(decodedResidentsFromLocationData.split(",")));
            for (int i = 0; i < existingData.size(); i++) {
                arrayResidents.add(existingData.get(i).replaceAll("\"|(^\\[|\\]$)", ""));
            }
            return arrayResidents;
        } catch (Exception e) {
            return arrayResidents;
        }
    }

}
