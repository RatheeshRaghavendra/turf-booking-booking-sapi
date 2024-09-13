package com.turf_booking.booking_sapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turf_booking.booking_sapi.dao.BookingDao;
import com.turf_booking.booking_sapi.model.ApiError;
import com.turf_booking.booking_sapi.model.ApiResponse;
import com.turf_booking.booking_sapi.model.Booking;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;
	
	public ResponseEntity<ApiResponse<String>> addBooking(Booking booking) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		ApiError apiError = new ApiError();
		String customError = "";
		
		try {
			
			Booking bookingResponse = bookingDao.save(booking);
			apiResponse.setPayload("The Booking was successful. Here is the Booking ID: " + bookingResponse.getBookingId().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			customError = "Error while adding the Booking";
			
			apiError.setApiErrorDetails(e, customError);
			
			apiResponse.setApiError(apiError);
			apiResponse.setStatusCode(500);
		}
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<Booking>> getBookingById(Integer bookingId) {
		
		ApiResponse<Booking> apiResponse = new ApiResponse<>();
		ApiError apiError = new ApiError();
		String customError = "";
		
		try {
			
			Booking booking = bookingDao.findById(bookingId).get();
			apiResponse.setPayload(booking);
			
		} catch (Exception e) {
			e.printStackTrace();

			customError = "Error while retrieving the Booking with Booking ID: " + bookingId;
			
			apiError.setApiErrorDetails(e, customError);
			
			apiResponse.setApiError(apiError);
			apiResponse.setStatusCode(500);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<List<Booking>>> getbookingByParameter(String parameter, Integer value) {
		
		ApiResponse<List<Booking>> apiResponse = new ApiResponse<>();
		ApiError apiError = new ApiError();
		String customError = "";
		
		try {
			
			List<Booking> bookingList = new ArrayList<>();
			
			switch (parameter.toLowerCase()){
				
			case "turf": 
				
				bookingList = bookingDao.findByTurfId(value);
				break;
			
			case "user": 
				
				bookingList = bookingDao.findByUserId(value);
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + parameter);
			}
			
			apiResponse.setPayload(bookingList);
				
		} catch (Exception e) {
			e.printStackTrace();

			customError = "Error while fetching the Bookings by Parameter(" + parameter.toUpperCase() + ") as " + value;
			
			apiError.setApiErrorDetails(e, customError);
			
			apiResponse.setApiError(apiError);
			apiResponse.setStatusCode(500);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
		
		ApiResponse<List<Booking>> apiResponse = new ApiResponse<>();
		ApiError apiError = new ApiError();
		String customError = "";
		
		try {
			
			List<Booking> bookingList = bookingDao.findAll();
			apiResponse.setPayload(bookingList);

		} catch (Exception e) {
			e.printStackTrace();

			customError = "Error while getting all Bookings";
			
			apiError.setApiErrorDetails(e, customError);
			
			apiResponse.setApiError(apiError);
			apiResponse.setStatusCode(500);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

	public ResponseEntity<ApiResponse<String>> cancelBooking(Integer bookingId) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		ApiError apiError = new ApiError();
		String customError = "";
		
		try {
			
			bookingDao.deleteById(bookingId);
			apiResponse.setPayload("The Booking with Booking ID: " + bookingId + " was cancelled");
			
		} catch (Exception e) {
			e.printStackTrace();

			customError = "Error while cancelling the Booking";
			
			apiError.setApiErrorDetails(e, customError);
			
			apiResponse.setApiError(apiError);
			apiResponse.setStatusCode(500);
		}
		
		return new ResponseEntity<>(apiResponse,apiResponse.getStatusMessage());
	}

}
