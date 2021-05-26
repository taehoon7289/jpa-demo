package com.dev.blackmango.repository;

import com.dev.blackmango.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserNo(long userNo);

  Optional<User> findById(String id);

  Optional<User> findByName(String name);

  List<User> findByNameLike(String name);

  Optional<User> findByIdAndName(String id, String name);

  Page<User> findPageByIdAndName(String id, String name, Pageable pageable);
}
