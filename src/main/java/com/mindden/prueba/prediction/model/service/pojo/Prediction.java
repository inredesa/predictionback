package com.mindden.prueba.prediction.model.service.pojo;

import com.mindden.prueba.prediction.model.service.enums.TemperatureUnitEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prediction {
    private List<PrecipitationProbability> precipitationProbabilityList;
    private float averageTemperature;
    private TemperatureUnitEnum temperatureUnit;
}
