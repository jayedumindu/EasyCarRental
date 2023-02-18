package service;

import dto.userDTO;

import java.util.ArrayList;

public interface userService {
    public void saveCustomer(userDTO dto);
    public void deleteCustomer(userDTO id);
    public void updateCustomer(userDTO dto);
    public ArrayList<userDTO> getAllCustomers();

    public userDTO searchCustomerWithName(String name);
}
