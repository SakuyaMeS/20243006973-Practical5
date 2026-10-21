package com.example.practical5;
public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String name = municipality.trim();

        if (name.equalsIgnoreCase("Beijing")) {
            return "Beijing";
        } else if (name.equalsIgnoreCase("Shanghai")) {
            return "Shanghai";
        } else if (name.equalsIgnoreCase("Chongqing")) {
            return "Chongqing";
        } else if (name.equalsIgnoreCase("Tianjin")) {
            return "Tianjin";
        }

        return null;
    }
}
