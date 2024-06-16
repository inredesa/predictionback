package com.mindden.prueba.prediction.model.service.mapper;

import com.mindden.prueba.prediction.model.accessor.pojo.ApiMunicipality;
import com.mindden.prueba.prediction.model.service.pojo.Municipality;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper {

    List<Municipality> toMunicipalityList(List<ApiMunicipality> apiMunicipalityList);

    default Municipality toMunicipality(ApiMunicipality apiMunicipality) {
        Municipality municipality = new Municipality();
        municipality.setId(apiMunicipality.getId().replace("id", ""));
        municipality.setName(apiMunicipality.getNombre());
        return municipality;
    }

}
