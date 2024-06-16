package com.mindden.prueba.prediction.model.accessor;

import com.mindden.prueba.prediction.model.accessor.pojo.ApiMunicipality;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiPredictionResponse;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiPredictionUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AemetAccessorImpl implements AemetAccessor {

    @Value("${aemet.baseurl}")
    private String aemetBaseUrl;

    @Value("${aemet.apikey}")
    private String apikey;

    private static String GET_MUNICIPALITIES_PATH = "maestro/municipios";
    private static String GET_PREDICTION_PATH = "prediccion/especifica/municipio/diaria/";

    private final RestTemplate restTemplate;

    /**
     * Obtiene una lista con todos los municipios registrados en AEMET.
     * @return Lista de municipios obtenida.
     */
    @Override
    @Cacheable("municipalities")
    public List<ApiMunicipality> getAllMunicipalities() {
        ResponseEntity<List<ApiMunicipality>> responseEntity = restTemplate.exchange(
                aemetBaseUrl + GET_MUNICIPALITIES_PATH,
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }

    /**
     * Obtiene la predicción de AEMET para un determinado municipio.
     * @param municipalityId Identificador del municipio.
     * @return Predicción obtenida.
     */
    @Override
    public ApiPredictionResponse getPrediction(String municipalityId) {
        ResponseEntity<ApiPredictionUrl> responseEntityUrl = restTemplate.exchange(
                aemetBaseUrl + GET_PREDICTION_PATH + municipalityId,
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<>() {});

        if (responseEntityUrl.getBody() == null || responseEntityUrl.getBody().getEstado() != 200) {
            return null;
        }

        String apiPredictionUrl = Objects.requireNonNull(responseEntityUrl.getBody()).getDatos();

        ResponseEntity<List<ApiPredictionResponse>> responseEntityPrediction = restTemplate.exchange(
                apiPredictionUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if (responseEntityPrediction.getBody() == null || responseEntityPrediction.getBody().isEmpty()) return null;

        return responseEntityPrediction.getBody().getFirst();
    }

    /**
     * Genera y devuelve un objeto HttpEntity con un encabezado al que se le ha añadido el ApiKey.
     * @return Encabezado generado.
     */
    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("api_key", apikey);

        return new HttpEntity<>(null, headers);
    }
}
