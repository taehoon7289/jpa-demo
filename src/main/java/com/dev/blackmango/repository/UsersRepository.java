package com.dev.blackmango.repository;

import com.dev.blackmango.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(String id);
    Optional<Users> findByName(String name);
    List<Users> findByNameLike(String name);
    Optional<Users> findByIdAndName(String id, String name);

    Page<Users> findPageByIdAndName(String id, String name, Pageable pageable);
}
