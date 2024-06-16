package com.mindden.prueba.prediction.controller.advice;

import com.mindden.prueba.prediction.model.exception.MunicipalityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = {"com.mindden.prueba.prediction.controller"})
public class ApiAdvice {

    @ExceptionHandler(MunicipalityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void municipalityNotFoundError(MunicipalityNotFoundException e) {
    }

}
