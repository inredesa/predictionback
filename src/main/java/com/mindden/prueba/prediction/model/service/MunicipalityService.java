package com.mindden.prueba.prediction.model.service;

import com.mindden.prueba.prediction.model.service.pojo.Municipality;

import java.util.List;

public interface MunicipalityService {
    List<Municipality> findMunicipalities(String municipalityName);
}
