package com.dev.blackmango.repository;

import com.dev.blackmango.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<User> findByName(String name);
    List<User> findByNameLike(String name);
    Optional<User> findByIdAndName(String id, String name);

    Page<User> findPageByIdAndName(String id, String name, Pageable pageable);
}
