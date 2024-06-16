package com.mindden.prueba.prediction.model.service;

import com.mindden.prueba.prediction.model.accessor.AemetAccessor;
import com.mindden.prueba.prediction.model.accessor.pojo.ApiMunicipality;
import com.mindden.prueba.prediction.model.service.mapper.MunicipalityMapper;
import com.mindden.prueba.prediction.model.service.pojo.Municipality;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MunicipalityServiceImpl implements MunicipalityService {

    private final AemetAccessor aemetAccessor;
    private final MunicipalityMapper municipalityMapper;

    /**
     * Recupera aquellos municipios cuyo nombre contiene el texto que recibe como parámetro.
     * @param municipalityName Texto que deben contener los nombres de los municipios.
     * @return Lista con los municipios obtenidos.
     */
    @Override
    public List<Municipality> findMunicipalities(String municipalityName) {
        List<ApiMunicipality> apiMunicipalityListFiltered =
                aemetAccessor.getAllMunicipalities().stream()
                        .filter(apiMunicipality -> apiMunicipality.getNombre().toLowerCase()
                                .contains(municipalityName.toLowerCase())).toList();

        return municipalityMapper.toMunicipalityList(apiMunicipalityListFiltered);
    }

}
