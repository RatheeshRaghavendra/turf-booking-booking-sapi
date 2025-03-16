package com.turf_booking.booking_sapi.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer bookingId;
	Integer turfId;
	@ElementCollection
	List<String> slotIds;
	Integer userId;
	Integer price = 0;
	
	public Booking(Integer bookingId, Integer turfId, List<String> slotIds, Integer userId, Integer price) {
		super();
		this.bookingId = bookingId;
		this.turfId = turfId;
		this.slotIds = slotIds;
		this.userId = userId;
		this.price = price;
	}
	
}
