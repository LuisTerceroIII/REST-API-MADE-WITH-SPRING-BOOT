package com.insurance.api.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//ESTA CLASE NOS PERMITE CUSTOMIZAR EL STATUS HTTP DE NUESTROS RESPONSE ENTITY, DADO QUE EL PREDETERMINADO ES 500.
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Let Spring BasicErrorController handle the exception, we just override the status code
    @ExceptionHandler(NotExistingIdException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());//ASIGANMOS STATUS 404
    }
}
