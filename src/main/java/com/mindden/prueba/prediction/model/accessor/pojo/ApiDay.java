package com.mindden.prueba.prediction.model.accessor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiDay {
    private ApiTemperature temperatura;
    private List<ApiPrecipitationProbability> probPrecipitacion;
    private LocalDate fecha;
}
