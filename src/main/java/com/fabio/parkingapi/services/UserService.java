package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewUserDto;
import com.fabio.parkingapi.entities.UserModel;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public UserModel save(NewUserDto dto){
        UserModel userModel = mapper.map(dto,UserModel.class);
        userModel.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(userModel);
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("User not found with ID -> "+id));
    }

    public UserModel updateById(Long id, UserModel userModel){
        findById(id);
        return userRepository.save(userModel);
    }

    public void deleteById(Long id){
        findById(id);
        userRepository.deleteById(id);
    }


}
