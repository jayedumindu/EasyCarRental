package service;

import dto.carDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface carService {
    void saveCar(carDTO dto);
    void deleteCar(String regNo);
    void updateCar(carDTO dto);
    carDTO findCarByRegNo(String regNo);
    ArrayList<carDTO> getAllCar();
    int countCarsByAvailabilityIsTrue();
    int countCarsScheduled();
    int isCarAvailable(String regNo, LocalDate date1, LocalDate date2);
}
