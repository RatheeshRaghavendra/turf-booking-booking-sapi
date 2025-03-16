package com.turf_booking.booking_sapi.error;

public class BookingAlreadyExists extends CustomUserException {
    public BookingAlreadyExists(String message) {
        super(message);
    }
    public BookingAlreadyExists(Exception exception, String message) {
        super(exception,message);
    }
}
