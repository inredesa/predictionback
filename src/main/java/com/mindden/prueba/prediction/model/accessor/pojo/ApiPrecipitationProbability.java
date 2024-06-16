package com.mindden.prueba.prediction.model.accessor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiPrecipitationProbability {
    private Integer value;
    private String periodo;
}
