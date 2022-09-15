package ru.stand.contest.restapi_yandex.handler;

public class ValidationItemException extends RuntimeException{
    public ValidationItemException(String message) {
        super(message);
    }
}
