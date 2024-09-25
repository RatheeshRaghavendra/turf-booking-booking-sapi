package com.turf_booking.booking_sapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import com.turf_booking.booking_sapi.error.BookingNotFound;
import com.turf_booking.booking_sapi.error.BookingUnexpectedException;
import com.turf_booking.booking_sapi.error.InvalidBooking;
import com.turf_booking.booking_sapi.logger.GlobalLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.turf_booking.booking_sapi.dao.BookingDao;
import com.turf_booking.booking_sapi.model.ApiError;
import com.turf_booking.booking_sapi.model.ApiResponse;
import com.turf_booking.booking_sapi.model.Booking;

@Service
@Log4j2
public class BookingService {

	private String prefix = GlobalLog.prefix + getClass().getSimpleName() + "::";

	@Autowired
	BookingDao bookingDao;

	public ResponseEntity<ApiResponse<String>> addBooking(Booking booking) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		try {
			Booking bookingResponse = bookingDao.save(booking);
			apiResponse.setPayload("The Booking was successful. Here is the Booking ID: " + bookingResponse.getBookingId().toString());
		} catch (Exception e) {
			log.error(prefix + "addBooking::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new BookingUnexpectedException(e,"Error while adding the Booking");
		}

		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<Booking>> getBookingById(Integer bookingId) {
		
		ApiResponse<Booking> apiResponse = new ApiResponse<>();
		try {
			Booking booking = bookingDao.findById(bookingId).orElseThrow();
			apiResponse.setPayload(booking);
		} catch (NoSuchElementException e) {
			log.error(prefix + "getBookingById::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new BookingNotFound("Booking with Booking ID: " + bookingId + " was not found");
		} catch (Exception e) {
			log.error(prefix + "getBookingById::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new BookingUnexpectedException(e,"Unexpected Error while getting the Booking with Booking ID: " + bookingId);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<List<Booking>>> getBookingByParameter(String parameter, Integer value) {

		String functionPrefix = prefix + "getBookingByParameter::";
		ApiResponse<List<Booking>> apiResponse = new ApiResponse<>();
		try {
			List<Booking> bookingList = switch (parameter.toLowerCase()) {
                case "turf" -> bookingDao.findByTurfId(value);
                case "user" -> bookingDao.findByUserId(value);
                default -> throw new IllegalArgumentException("Unexpected value: " + parameter);
            };
			if(bookingList.isEmpty())
				throw new BookingNotFound("No Bookings found for the Parameter(" + parameter.toUpperCase() + ") as Value: " + value);
            apiResponse.setPayload(bookingList);
		} catch (IllegalArgumentException e) {
			log.error(functionPrefix + "CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new InvalidBooking(e,"Invalid Parameter: " + parameter.toUpperCase());
		} catch (BookingNotFound e) {
			log.error(functionPrefix + "CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(functionPrefix + "CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new InvalidBooking(e,"Error while fetching the Bookings by Parameter(" + parameter.toUpperCase() + ") as " + value);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
		
		ApiResponse<List<Booking>> apiResponse = new ApiResponse<>();
		try {
			List<Booking> bookingList = bookingDao.findAll();
			apiResponse.setPayload(bookingList);
		} catch (Exception e) {
			log.error(prefix + "getAllBookings::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new InvalidBooking(e,"Error while fetching all the Bookings");
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<String>> cancelBooking(Integer bookingId) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		try {
			if(!bookingDao.existsById(bookingId))
				throw new BookingNotFound("Booking with ID: " + bookingId + " doesn't exist");
			bookingDao.deleteById(bookingId);
			apiResponse.setPayload("The Booking with Booking ID: " + bookingId + " was cancelled");
		} catch (BookingNotFound e) {
			log.error(prefix + "cancelBooking::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(prefix + "cancelBooking::CAUSE::" + e.getClass().getSimpleName() + "::DESCRIPTION::" + e.getMessage());
			throw new InvalidBooking(e,"Error while cancelling the Booking with Booking ID: " + bookingId);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

}
