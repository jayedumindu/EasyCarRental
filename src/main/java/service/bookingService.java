package service;

import dto.bookingDTO;
import dto.paymentDTO;

public interface bookingService {
    void placeBooking(bookingDTO dto1, paymentDTO dto2);
    String getLastBookingId();
}
