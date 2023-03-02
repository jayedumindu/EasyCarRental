package service.impl;

import dto.adminDTO;
import dto.userDTO;
import entity.Admin;
import entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.adminRepo;
import repo.userRepo;
import service.userService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class userServiceImpl implements userService {

    @Autowired
    private userRepo repo;

    @Autowired
    private adminRepo adminRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveUser(userDTO dto) {
        if (repo.existsById(dto.getUsername())){
            throw new RuntimeException("Customer Already Exist. Please enter another id..!");
        }
        repo.save(mapper.map(dto, User.class));
    }

    @Override
    public void deleteUser(String id) {
        if (!repo.existsById(id)){
            throw new RuntimeException("Wrong ID..Please enter valid id..!");
        }
        repo.deleteById(id);
    }

    @Override
    public void updateUser(userDTO dto) {
        if (!repo.existsById(dto.getUsername())){
            throw new RuntimeException("Customer Not Exist. Please Enter Valid ID..!");
        }
        repo.updateUser(dto.getUsername(),dto.getAddress(),dto.getContact(),dto.getLicense(),dto.getName(),dto.getNic());
    }

    @Override
    public void saveOrUpdate(userDTO dto) {
        repo.save(mapper.map(dto, User.class));
    }

    @Override
    public userDTO findUserByUsername(String username) {
        return mapper.map(repo.findUserByUsername(username), userDTO.class);
    }

    @Override
    public ArrayList<userDTO> getAllUser() {
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<userDTO>>(){}.getType());
    }

    @Override
    public adminDTO validateAdmin(String id) {
        Admin ent = adminRepo.findAdminByUsername(id);
        if (ent != null) {
            return mapper.map(ent, adminDTO.class);
        }
        return null;

    }

}
