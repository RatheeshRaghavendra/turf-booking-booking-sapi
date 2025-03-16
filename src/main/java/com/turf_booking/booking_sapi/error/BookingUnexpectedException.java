package com.turf_booking.booking_sapi.error;

public class BookingUnexpectedException extends CustomUserException {
    public BookingUnexpectedException(String message) {
        super(message);
    }
    public BookingUnexpectedException(Exception e, String message) {
        super(e,message);
    }
}
