package service;

import dto.driverDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface driverService {
    void saveDriver(driverDTO dto);
    void deleteDriver(String username);
    void updateDriver(driverDTO dto);
    driverDTO findDriverByUsername(String username);
    ArrayList<driverDTO> getAllDriver();
    driverDTO findDriverRandomly(LocalDate date1, LocalDate date2);
    int getNoOfOccupiedDrivers();
    int getNoOfAvailableDrivers();
}
