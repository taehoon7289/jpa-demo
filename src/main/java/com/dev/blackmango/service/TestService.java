package com.dev.blackmango.service;

import com.dev.blackmango.dto.signup.SignUpUser;
import com.dev.blackmango.entity.Users;
import com.dev.blackmango.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private UsersRepository usersRepository;

    public List<Users> getListUsers() {
        return usersRepository.findAll();
    }

    public Users postUsers(SignUpUser signUpUser) {
        Users users = Users.builder()
                .id(signUpUser.getId())
                .password(signUpUser.getPassword())
                .build();
        return usersRepository.save(users);
    }
}
