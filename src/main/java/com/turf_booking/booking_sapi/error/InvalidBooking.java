package com.turf_booking.booking_sapi.error;

public class InvalidBooking extends CustomUserException {
    public InvalidBooking(String message) {
        super(message);
    }
    public InvalidBooking(Exception exception, String message) {
      super(exception, message);
    }
}
