package service;

import dto.carDTO;

import java.util.ArrayList;

public interface carService {
    void saveCar(carDTO dto);
    void deleteCar(String regNo);
    void updateCar(carDTO dto);
    ArrayList<carDTO> getAllCar();
}
