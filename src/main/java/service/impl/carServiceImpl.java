package service.impl;

import dto.userDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import repo.userRepo;
import service.carService;

import java.util.ArrayList;

public class carServiceImpl implements carService {

    @Autowired
    private userRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveUser(userDTO dto) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void updateUser(userDTO dto) {

    }

    @Override
    public ArrayList<userDTO> getAllUser() {
        return null;
    }
}
