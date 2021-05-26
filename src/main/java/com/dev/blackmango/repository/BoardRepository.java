package com.dev.blackmango.repository;

import com.dev.blackmango.model.entity.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  Optional<Board> findByBoardNo(long boardNo);
}
