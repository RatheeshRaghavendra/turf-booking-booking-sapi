package com.turf_booking.booking_sapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turf_booking.booking_sapi.dao.BookingDao;
import com.turf_booking.booking_sapi.model.Booking;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;
	
	public ResponseEntity<String> addBooking(Booking booking) {
		try {
			Booking bookingResponse = bookingDao.save(booking);
			return new ResponseEntity<>("The Booking was successful. Here is the Booking ID: " + bookingResponse.getBookingId().toString(),HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Booking> getBookingById(Integer bookingId) {
		
		try {
			Booking booking = bookingDao.findById(bookingId).get();
			return new ResponseEntity<>(booking,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<Booking>> getbookingByParameter(String parameter, Integer value) {
		
		List<Booking> bookingList = new ArrayList<>();
		
		try {
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
			
			return new ResponseEntity<>(bookingList,HttpStatus.OK);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<Booking>> getAllBookings() {
		
		try {
			List<Booking> bookingList = bookingDao.findAll();
			return new ResponseEntity<>(bookingList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
