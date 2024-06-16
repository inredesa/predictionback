package com.mindden.prueba.prediction.model.accessor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiPrediction {
    private List<ApiDay> dia;
}
