package service.impl;

import dto.userDTO;
import entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        repo.save(mapper.map(dto, User.class));
    }

    @Override
    public void saveOrUpdate(userDTO dto) {
        repo.save(mapper.map(dto, User.class));
    }

    @Override
    public ArrayList<userDTO> getAllUser() {
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<userDTO>>(){}.getType());
    }

}
