package com.turf_booking.booking_sapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turf_booking.booking_sapi.model.Booking;
import com.turf_booking.booking_sapi.service.BookingService;

@RestController
@RequestMapping("api/booking-sapi")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("live")
	public String getHealth() {
		return "live";
	}
	
	@PostMapping("booking")
	public ResponseEntity<String> addBooking (@RequestBody Booking booking){
		return bookingService.addBooking(booking);
	}
	
	@GetMapping("booking/{bookingId}")
	public ResponseEntity<Booking> getBooking (@PathVariable Integer bookingId){
		return bookingService.getBookingById(bookingId);
	}
	
	@GetMapping("booking/search-by")
	public ResponseEntity<List<Booking>> getBookingBy (@RequestParam String parameter,@RequestParam Integer value){
		return bookingService.getbookingByParameter(parameter,value);
	}
	
	@GetMapping("booking/all")
	public ResponseEntity<List<Booking>> getAllBookings (){
		return bookingService.getAllBookings();
	}

}
