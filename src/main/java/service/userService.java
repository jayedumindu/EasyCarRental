package service;

import dto.adminDTO;
import dto.userDTO;

import java.util.ArrayList;

public interface userService {
    void saveUser(userDTO dto);
    void deleteUser(String username);
    void updateUser(userDTO dto);
    void saveOrUpdate(userDTO dto);
    userDTO findUserByUsername(String username);
    ArrayList<userDTO> getAllUser();
    adminDTO validateAdmin(String id);
}
