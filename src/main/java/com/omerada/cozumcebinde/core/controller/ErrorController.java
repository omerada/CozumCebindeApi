package com.omerada.cozumcebinde.core.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 * This class handles the error occurred. We have configure to return proper detail
 */
@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {

    private final ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes=errorAttributes;
    }

    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, ErrorAttributeOptions options) {
        return super.getErrorAttributes((javax.servlet.http.HttpServletRequest) request, options);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request, WebRequest webRequest) {
        Map<String, Object> body = getErrorAttributes(webRequest);
        HttpStatus status = getStatus((javax.servlet.http.HttpServletRequest) request);
        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return this.errorAttributes.getErrorAttributes(webRequest,
                ErrorAttributeOptions.defaults());
    }
}
