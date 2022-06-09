package com.acmebank.account.exception;

import com.acmebank.account.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFountExceptions(Exception ex, WebRequest request) {
        Response response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.EntityBadRequestException.class)
    public final ResponseEntity handleBadRequestExceptions(Exception ex, WebRequest request) {
        Response response = Response.badRequest();
        response.addErrorMsgToResponse(ex.getMessage(), ex);

        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }
}