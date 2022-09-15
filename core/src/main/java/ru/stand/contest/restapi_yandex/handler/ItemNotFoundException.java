package ru.stand.contest.restapi_yandex.handler;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }

}
