package com.mindden.prueba.prediction.controller;

import com.mindden.prueba.prediction.model.service.PredictionService;
import com.mindden.prueba.prediction.model.service.enums.TemperatureUnitEnum;
import com.mindden.prueba.prediction.model.service.pojo.Prediction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Prediction API")
@RequestMapping("/api/v1/prediction")
public class PredictionController {

    private final PredictionService predictionService;

    @GetMapping
    @Operation(summary = "Obtiene la predicción para el día de mañana del municipio establecido.")
    public Prediction getPrediction(
            @Parameter(name = "municipalityId", description = "Identificador del municipio", example = "15019")
            @RequestParam String municipalityId,
            @Parameter(name = "temperatureUnitEnum", description = "Unidad de medida de temperatura", example = "G_CEL")
            @RequestParam TemperatureUnitEnum temperatureUnitEnum
    ) {
        return predictionService.getPrediction(municipalityId, temperatureUnitEnum);
    }

}
