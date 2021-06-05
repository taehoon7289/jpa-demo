package com.dev.blackmango.service;

import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.exception.code.BoardExCode;
import com.dev.blackmango.common.exception.code.UserExCode;
import com.dev.blackmango.model.dto.board.BoardDTO;
import com.dev.blackmango.model.entity.Board;
import com.dev.blackmango.model.entity.QBoard;
import com.dev.blackmango.model.entity.User;
import com.dev.blackmango.repository.BoardRepository;
import com.dev.blackmango.repository.UserRepository;
import com.dev.blackmango.repository.query.BoardQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

  private final QBoard qBoard;
  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final BoardQueryRepository boardQueryRepository;
  private final ObjectMapper objectMapper;

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

  @Transactional
  public Map getBoard(String title) {
    Board board = boardQueryRepository.getBoard(title);
//    User user = board.getUser();
//    log.info("boards :: {}", user.getBoards());
    Map resultMap = objectMapper.convertValue(board, Map.class);
    User user = board.getUser();
    Set<Board> tempBoards = user.getBoards();
    Map userMap = objectMapper.convertValue(user, Map.class);
    List<Map> boardMaps = new ArrayList<>();
    for(Board board1 : tempBoards) {
      boardMaps.add(objectMapper.convertValue(board1, Map.class));
    }
    userMap.put("boards", boardMaps);
    resultMap.put("user", userMap);
    return resultMap;
  }

}
