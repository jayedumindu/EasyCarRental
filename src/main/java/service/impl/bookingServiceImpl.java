package service.impl;

import dto.bookingDTO;
import dto.paymentDTO;
import entity.Booking;
import entity.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import repo.bookingRepo;
import repo.paymentRepo;
import service.bookingService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;


@Service
@Transactional
public class bookingServiceImpl implements bookingService {

    @Autowired
    private bookingRepo bookingRepo;

    @Autowired
    private paymentRepo paymentRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void placeBooking(bookingDTO dto1, paymentDTO dto2) {
        bookingRepo.save(mapper.map(dto1, Booking.class));
        paymentRepo.save(mapper.map(dto2, Payment.class));
    }

    @Override
    public String getLastBookingId() {
        return bookingRepo.getLastBookingId();
    }

    @Override
    public int getBookingActiveToday() {
        return bookingRepo.getBookingActiveToday();
    }

    @Override
    public int getBookingsForToday() {
        return bookingRepo.getBookingsForToday();
    }

    @Override
    public Collection<bookingDTO> getBookingsByAcceptedFalse() {
        return mapper.map(bookingRepo.getGroupDetails(),new TypeToken<Collection<bookingDTO>>(){}.getType());

    }

    @Override
    public void upDatePayment(paymentDTO dto, String bId) {
paymentRepo.updatePayment(dto.getRent(),dto.getAccount(),dto.getDeduction(),dto.getPayment_method(),dto.getReturnDateTime(),bId);
    }


}
