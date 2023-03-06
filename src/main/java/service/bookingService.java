package service;

import dto.bookingDTO;
import dto.paymentDTO;
import entity.Booking;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface bookingService {
    void placeBooking(bookingDTO dto1, paymentDTO dto2);
    String getLastBookingId();
    int getBookingActiveToday();
    int getBookingsForToday();
    Collection<bookingDTO> getBookingsByAcceptedFalse();
void upDatePayment(paymentDTO dto, String bId);
void removeBooking(String id);
void acceptBooking(String id);
    Collection<bookingDTO> findBookingsForUser(String id);
    boolean checkIfPaymentExist(String pId);
    bookingDTO getBookingById(String bId);
    paymentDTO getPaymentById(String pId);
}
