package com.turf_booking.booking_sapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turf_booking.booking_sapi.model.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByTurfId(Integer turfId);
	
	List<Booking> findByUserId(Integer userId);

}
