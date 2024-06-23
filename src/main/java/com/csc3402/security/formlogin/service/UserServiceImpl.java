package com.csc3402.security.formlogin.service;

import com.csc3402.security.formlogin.dto.UserDto;
import com.csc3402.security.formlogin.model.Role;
import com.csc3402.security.formlogin.model.User;
import com.csc3402.security.formlogin.repository.RoleRepository;
import com.csc3402.security.formlogin.repository.UserRepository;
import com.csc3402.security.formlogin.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto){
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}