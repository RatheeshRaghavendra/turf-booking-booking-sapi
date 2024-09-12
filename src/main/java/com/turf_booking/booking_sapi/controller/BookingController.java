package com.turf_booking.booking_sapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booking")
public class BookingController {
	
	@GetMapping("live")
	public String getHealth() {
		return "live";
	}

}
