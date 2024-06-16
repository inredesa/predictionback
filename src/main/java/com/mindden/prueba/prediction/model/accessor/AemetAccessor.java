package com.mindden.prueba.prediction.model.accessor;

import com.mindden.prueba.prediction.model.accessor.pojo.ApiMunicipality;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiPredictionResponse;

import java.util.List;

public interface AemetAccessor {
    List<ApiMunicipality> getAllMunicipalities();
    ApiPredictionResponse getPrediction(String municipalityId);
}
