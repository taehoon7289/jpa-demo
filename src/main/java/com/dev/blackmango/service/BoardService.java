package com.dev.blackmango.service;

import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.exception.code.BoardExCode;
import com.dev.blackmango.common.exception.code.UserExCode;
import com.dev.blackmango.model.dto.board.BoardDTO;
import com.dev.blackmango.model.entity.Board;
import com.dev.blackmango.model.entity.User;
import com.dev.blackmango.repository.BoardRepository;
import com.dev.blackmango.repository.UserRepository;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class BoardService {

  @PersistenceContext
  private final EntityManager entityManager;
  private final UserRepository userRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public long saveBoard(long userNo, BoardDTO boardDTO) throws ServiceException {
    User user = userRepository.findByUserNo(userNo)
        .orElseThrow(() -> new ServiceException(UserExCode.NO_DATA));
    if (ObjectUtils.isEmpty(boardDTO.getBoardNo()) || boardDTO.getBoardNo() < 1) {
      // 신규작성
      Board board = Board.builder()
          .title(boardDTO.getTitle())
          .contents(boardDTO.getContents())
          .regDate(LocalDateTime.now())
          .uptDate(LocalDateTime.now())
          .user(user)
          .build();
      boardRepository.save(board);
      return board.getBoardNo();
    }
    // 수정
    Board board = boardRepository.findByBoardNo(boardDTO.getBoardNo())
        .orElseThrow(() -> new ServiceException(BoardExCode.NO_DATA));
    board.setTitle(boardDTO.getTitle());
    board.setContents(boardDTO.getContents());
    board.setUptDate(LocalDateTime.now());
    return board.getBoardNo();
  }

  public Board getBoard(String title) {
    TypedQuery query = entityManager
        .createQuery("select b from Board b where b.title = :title", Board.class)
        .setParameter("title", title);
    Board board = (Board) query.getSingleResult();
    return board;
  }

}
