package com.dev.blackmango.service;

import com.dev.blackmango.dto.signup.SignUpForm;
import com.dev.blackmango.entity.User;
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
public class UserService {

    private UsersRepository usersRepository;

    public List<User> getListUsers() {
        return usersRepository.findAll();
    }

    public User postUsers(SignUpForm signUpForm) {
        User user = new User();
        user.setId(signUpForm.getId());
        user.setPassword(signUpForm.getPassword());
        user.setName(signUpForm.getName());
        return usersRepository.save(user);
    }

    public User getUsersById(String id) {
        Optional<User> user = usersRepository.findById(id);
        return user.orElseGet(() -> null);
    }

    public User getUsersByName(String name) {
        Optional<User> user = usersRepository.findByName(name);
        return user.orElseGet(() -> null);
    }

    public List<User> getUsersByNameLike(String name) {
        return usersRepository.findByNameLike(name);
    }

    public User getUsersByIdAndName(String id, String name) {
        Optional<User> user = usersRepository.findByIdAndName(id, name);
        return user.orElseGet(() -> null);
    }

    public Page<User> getUsersPage(int page, int limit) {
        // paging 처리, sort 관련 방법
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").descending().and(Sort.by("userNo").ascending()));
        return usersRepository.findAll(pageable);
    }

    public Page<User> getUsersPageByIdAndName(int page, int limit, String id, String name) {
        Pageable pageable = PageRequest.of(page, limit);
        return usersRepository.findPageByIdAndName(id, name, pageable);
    }

}
