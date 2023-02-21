package service;

import dto.userDTO;

import java.util.ArrayList;

public interface carService {
    void saveUser(userDTO dto);
    void deleteUser(String username);
    void updateUser(userDTO dto);
    ArrayList<userDTO> getAllUser();
}
