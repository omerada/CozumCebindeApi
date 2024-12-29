package com.omerada.cozumcebinde.core.exception;

import com.ibm.db2.jcc.b.ConversionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.omerada.cozumcebinde.core.payload.ServiceErrorResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(ServiceException ex) {
        ServiceErrorResponse serviceErrorResponse = new ServiceErrorResponse();
        serviceErrorResponse.setMessage(ex.getLocalizedMessage());
        serviceErrorResponse.setSuccess(false);
        serviceErrorResponse.setDetailMessage(ex.getStackTrace().toString());
        serviceErrorResponse.setHttpCode(HttpStatus.OK.toString());
        return new ResponseEntity<>(serviceErrorResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<?> handleConversionException(ConversionException ex) {
        ServiceErrorResponse serviceErrorResponse = new ServiceErrorResponse();
        serviceErrorResponse.setMessage(ex.getMessage());
        serviceErrorResponse.setSuccess(false);
        serviceErrorResponse.setDetailMessage(ex.toString());
        serviceErrorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(serviceErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}