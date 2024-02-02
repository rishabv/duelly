package com.duelly.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.dtos.responses.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseApiResponse<String> processValidationError(MethodArgumentNotValidException ex) {
        var result = ex.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        List<ErrorDto> errors = allErrors.stream().map(FieldError.class::cast)
                .map(err -> new ErrorDto(err.getDefaultMessage(), err.getField(), err.getRejectedValue())).toList();
        return new BaseApiResponse<>(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public BaseApiResponse<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new BaseApiResponse<>(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseApiResponse<String> handleUnknownError(Exception ex) {
        String message = ex.getMessage();
        log.info(message);
        if(ex instanceof MissingServletRequestParameterException exception) {
            message = "'" + exception.getParameterName() + "' is missing from request parameters";
        }
        String stackTrace = ExceptionUtils.getStackTrace(ex);
        log.error("Unknown error:" +  stackTrace + " with message: " + message);
        return new BaseApiResponse<>(new ErrorDto(message, stackTrace));
    }
}
