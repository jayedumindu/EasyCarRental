package service.impl;

import dto.driverDTO;
import entity.Driver;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.driverRepo;
import service.driverService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class driverServiceImpl implements driverService {

    @Autowired
    private driverRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveDriver(driverDTO dto) {
        if (repo.existsById(dto.getUsername())){
            throw new RuntimeException("Driver Already Exist. Please enter another username..!");
        }
        repo.save(mapper.map(dto, Driver.class));
    }

    @Override
    public void deleteDriver(String username) {
        if (!repo.existsById(username)){
            throw new RuntimeException("Wrong username.... Please enter valid username..!");
        }
        repo.deleteById(username);
    }

    @Override
    public void updateDriver(driverDTO dto) {
        if (!repo.existsById(dto.getUsername())){
            throw new RuntimeException("Driver Doesn't Exist. Please Enter Valid username..!");
        }
        repo.save(mapper.map(dto, Driver.class));
    }

    @Override
    public driverDTO findDriverByUsername(String username) {
        return mapper.map(repo.findDriverByUsername(username),driverDTO.class);
    }

    @Override
    public ArrayList<driverDTO> getAllDriver() {
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<driverDTO>>(){}.getType());
    }

    @Override
    public driverDTO findDriverRandomly() {
        return mapper.map(repo.selectDriverRandomly(),driverDTO.class);

    }

    @Override
    public int getNoOfOccupiedDrivers() {
        return repo.getNoOfOccupiedDrivers();
    }

    @Override
    public int getNoOfAvailableDrivers() {
        return repo.getNoOfAvailableDrivers();
    }
}
