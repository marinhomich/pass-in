package com.marinhomich.passin.domain.event.exceptions;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(String message){
        super(message);
    }
}
