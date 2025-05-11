package com.app.BookingService.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.BookingService.model.Booking;
import com.app.BookingService.model.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByFlightId(Long flightId);
	 List<Booking> findByEmail(String email);
	 List<Booking> findByStatus(BookingStatus status);

}
