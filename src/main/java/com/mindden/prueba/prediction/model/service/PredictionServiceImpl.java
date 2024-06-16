package com.mindden.prueba.prediction.model.service;

import com.mindden.prueba.prediction.model.accessor.AemetAccessor;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiDay;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiPredictionResponse;
import com.mindden.prueba.prediction.model.exception.MunicipalityNotFoundException;
import com.mindden.prueba.prediction.model.service.enums.TemperatureUnitEnum;
import com.mindden.prueba.prediction.model.service.mapper.PrecipitationProbabilityMapper;
import com.mindden.prueba.prediction.model.service.pojo.PrecipitationProbability;
import com.mindden.prueba.prediction.model.service.pojo.Prediction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PredictionServiceImpl implements PredictionService {

    private final AemetAccessor aemetAccessor;
    private final PrecipitationProbabilityMapper predictionProbabilityMapper;

    /**
     * Obtiene la predicción para el día de mañana del municipio establecido.
     * @param municipalityId Identificador del municipio.
     * @param temperatureUnitEnum Unidad de medida de temperatura.
     * @return Predicción obtenida.
     */
    @Override
    public Prediction getPrediction(String municipalityId, TemperatureUnitEnum temperatureUnitEnum) {
        ApiPredictionResponse apiPredictionResponse = aemetAccessor.getPrediction(municipalityId);

        if (apiPredictionResponse == null) throw new MunicipalityNotFoundException();

        Optional<ApiDay> apiDayOptional =
                apiPredictionResponse.getPrediccion().getDia().stream().filter(dia -> isNextDay(dia.getFecha())).findFirst();

        if (apiDayOptional.isEmpty()) return null;

        Float maximun = apiDayOptional.get().getTemperatura().getMaxima();
        Float minimun = apiDayOptional.get().getTemperatura().getMinima();

        float averageTemperature = (maximun + minimun)/2;
        if (temperatureUnitEnum == TemperatureUnitEnum.G_FAH) {
            averageTemperature = (averageTemperature * (9f/5f)) + 32;
        }

        List<PrecipitationProbability> precipitationProbabilityList =
                predictionProbabilityMapper.toPrecipitationProbabilityList(apiDayOptional.get().getProbPrecipitacion());

        Prediction prediction = new Prediction();
        prediction.setAverageTemperature(averageTemperature);
        prediction.setTemperatureUnit(temperatureUnitEnum);
        prediction.setPrecipitationProbabilityList(precipitationProbabilityList);

        return prediction;
    }

    /**
     * Indica si la fecha que recibe como parámetro corresponde a la del día siguiente al actual.
     * @param dateToCompare Fecha que se ha de analizar.
     * @return True o False dependiendo de si la fecha corresponde o no con la del día siguiente.
     */
    private boolean isNextDay(LocalDate dateToCompare) {
        LocalDate nextDay = LocalDate.now().plusDays(1);
        return dateToCompare.getYear() == nextDay.getYear() && dateToCompare.getDayOfYear() == nextDay.getDayOfYear();
    }

}
