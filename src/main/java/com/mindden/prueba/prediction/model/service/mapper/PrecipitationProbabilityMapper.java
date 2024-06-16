package com.mindden.prueba.prediction.model.service.mapper;

import com.mindden.prueba.prediction.model.accessor.pojo.ApiPrecipitationProbability;
import com.mindden.prueba.prediction.model.service.pojo.PrecipitationProbability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrecipitationProbabilityMapper {

    @Mapping(source = "periodo", target = "period")
    PrecipitationProbability toPrecipitationProbability(ApiPrecipitationProbability apiPrecipitationProbability);

    List<PrecipitationProbability> toPrecipitationProbabilityList(List<ApiPrecipitationProbability> apiPrecipitationProbabilityList);

}
