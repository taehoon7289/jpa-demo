package com.dev.blackmango.service;

import com.dev.blackmango.dto.signup.SignUpUser;
import com.dev.blackmango.entity.Users;
import com.dev.blackmango.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .name(signUpUser.getName())
                .build();
        return usersRepository.save(users);
    }

    public Users getUsersById(String id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElseGet(() -> null);
    }

    public Users getUsersByName(String name) {
        Optional<Users> user = usersRepository.findByName(name);
        return user.orElseGet(() -> null);
    }

    public List<Users> getUsersByNameLike(String name) {
        return usersRepository.findByNameLike(name);
    }

    public Users getUsersByIdAndName(String id, String name) {
        Optional<Users> user = usersRepository.findByIdAndName(id, name);
        return user.orElseGet(() -> null);
    }

    public Page<Users> getUsersPage(int page, int limit) {
        // paging 처리, sort 관련 방법
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").descending().and(Sort.by("userNo").ascending()));
        return usersRepository.findAll(pageable);
    }

    public Page<Users> getUsersPageByIdAndName(int page, int limit, String id, String name) {
        Pageable pageable = PageRequest.of(page, limit);
        return usersRepository.findPageByIdAndName(id, name, pageable);
    }

}
