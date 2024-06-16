package com.mindden.prueba.prediction.model.service;

import com.mindden.prueba.prediction.model.service.enums.TemperatureUnitEnum;
import com.mindden.prueba.prediction.model.service.pojo.Prediction;

public interface PredictionService {
    Prediction getPrediction(String municipalityId, TemperatureUnitEnum temperatureUnitEnum);
}
