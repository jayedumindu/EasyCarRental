package service.impl;

import dto.carDTO;
import entity.Car;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.carRepo;
import service.carService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class carServiceImpl implements carService {

    @Autowired
    private carRepo repo;

    @Autowired
    private ModelMapper mapper;


    @Override
    public void saveCar(carDTO dto) {
        if (repo.existsById(dto.getRegistrationNumber())){
            throw new RuntimeException("Car Already Exist!");
        }
        repo.save(mapper.map(dto, Car.class));
    }

    @Override
    public void deleteCar(String regNo) {
        if (!repo.existsById(regNo)){
            throw new RuntimeException("Wrong Registration Number..Please enter a valid number !");
        }
        repo.deleteById(regNo);
    }

    @Override
    public void updateCar(carDTO dto) {
        if (!repo.existsById(dto.getRegistrationNumber())){
            throw new RuntimeException("Car Doesn't Exist. Please Enter Valid identifier..!");
        }
        repo.save(mapper.map(dto, Car.class));
    }

    @Override
    public carDTO findCarByRegNo(String regNo) {
        return mapper.map(repo.findCarByRegistrationNumber(regNo),carDTO.class);
    }

    @Override
    public ArrayList<carDTO> getAllCar() {
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<carDTO>>(){}.getType());
    }

    @Override
    public int countCarsByAvailabilityIsTrue() {
        return repo.countCarsByAvailabilityIsTrue();
    }

    @Override
    public int countCarsScheduled() {
        return repo.countCarsScheduled();
    }
}
