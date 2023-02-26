package service;

import dto.bookingDTO;
import dto.paymentDTO;

import java.util.Collection;

public interface bookingService {
    void placeBooking(bookingDTO dto1, paymentDTO dto2);
    String getLastBookingId();
    int getBookingActiveToday();
    int getBookingsForToday();
    Collection<bookingDTO> getBookingsByAcceptedFalse();
}
