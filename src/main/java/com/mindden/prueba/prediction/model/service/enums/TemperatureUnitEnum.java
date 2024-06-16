package com.mindden.prueba.prediction.model.service.enums;

public enum TemperatureUnitEnum {
    G_CEL("Grados Celsius"),
    G_FAH("Grados Fahrenheit");

    private final String description;

    TemperatureUnitEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
