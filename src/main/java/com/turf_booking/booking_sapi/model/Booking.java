package com.turf_booking.booking_sapi.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer bookingId;
	Integer turfId;
	@ElementCollection
	List<Integer> slotIds;
	Integer userId;
	
	public Booking(Integer bookingId, Integer turfId, List<Integer> slotIds, Integer userId) {
		super();
		this.bookingId = bookingId;
		this.turfId = turfId;
		this.slotIds = slotIds;
		this.userId = userId;
	}

	public Booking() {
		super();
	}
	
	
	
	
}
