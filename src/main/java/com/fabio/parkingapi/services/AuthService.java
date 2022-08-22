package com.fabio.parkingapi.services;

import com.fabio.parkingapi.entities.UserModel;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email){
        UserModel userModel = userRepository.findByEmail(email);
        if (userModel == null){
            throw new ObjectNotFoundException("Email not Found.");
        }

        String newPass = newPassword();
        userModel.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(userModel);
        emailService.sendNewPasswordEmail(userModel,newPass);
    }

    private String newPassword(){
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar(){
        int opt = rand.nextInt(3);
        if (opt == 0){ //gerar um digito
            return (char) (rand.nextInt(10) + 40);
        }else if(opt == 1){//gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        }else {//gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
