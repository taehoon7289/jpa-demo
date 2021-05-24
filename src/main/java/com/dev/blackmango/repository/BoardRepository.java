package com.dev.blackmango.repository;

import com.dev.blackmango.model.entity.Board;
import com.dev.blackmango.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  Page<Board> findByUserEquals(User user, Pageable pageable);
}
