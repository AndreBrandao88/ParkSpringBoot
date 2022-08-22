package com.fabio.parkingapi.security;

import com.fabio.parkingapi.entities.UserModel;
import com.fabio.parkingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);
        if (userModel == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(userModel.getId().intValue(),userModel.getEmail(),userModel.getPassword(),userModel.getPerfis());
    }
}
