package ru.stand.contest.restapi_yandex.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stand.contest.restapi_yandex.model.Error;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ItemExceptionHandler {


    @ExceptionHandler({
            ValidationItemException.class,
            HttpMessageNotReadableException.class,
            ConstraintViolationException.class,
            ItemNotFoundException.class,
            InvalidFormatException.class})
    protected ResponseEntity<Error> handleItemException(final RuntimeException ex) {

        if (ex instanceof ItemNotFoundException) {
            return new ResponseEntity<>(new Error().code(404).message(ex.getMessage()), HttpStatus.NOT_FOUND);
        }

        Error error = new Error();
        error.code(400).message("Validation Failed");
//        error.code(400).message(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

