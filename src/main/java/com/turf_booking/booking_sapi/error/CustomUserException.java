package com.turf_booking.booking_sapi.error;

import lombok.Getter;

@Getter
public class CustomUserException extends RuntimeException {

    Exception exception;

    public CustomUserException(String message) {
        super(message);
    }
    public CustomUserException(Exception exception, String message){
        super(message);
        this.exception = exception;
    }
}
