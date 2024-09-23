package com.turf_booking.booking_sapi.error;

public class BookingNotFound extends CustomUserException {

    public BookingNotFound(String message) {
        super(message);
    }
    public BookingNotFound(Exception e, String message) {
        super(e,message);
    }

}
