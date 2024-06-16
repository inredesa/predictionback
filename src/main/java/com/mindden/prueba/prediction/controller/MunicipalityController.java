package com.mindden.prueba.prediction.controller;

import com.mindden.prueba.prediction.model.service.MunicipalityService;
import com.mindden.prueba.prediction.model.service.pojo.Municipality;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/municipality")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @GetMapping
    @Operation(summary = "Recupera aquellos municipios cuyo nombre contiene el texto que recibe como parámetro.")
    public List<Municipality> findMunicipalities(
            @Parameter(name = "municipalityName", description = "Texto que deben contener los nombres de los municipios", example = "carba")
            @RequestParam String municipalityName
    ) {
        return municipalityService.findMunicipalities(municipalityName);
    }

}
