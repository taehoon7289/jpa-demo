package com.dev.blackmango.repository.query;

import com.dev.blackmango.model.entity.Board;
import com.dev.blackmango.model.entity.QBoard;
import com.dev.blackmango.model.entity.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;
//  private final EntityManager entityManager;
  private final QBoard qBoard;
  private final QUser qUser;

  public Board getBoard(String title) {
//    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
    return jpaQueryFactory
        .selectFrom(qBoard)
        .join(qBoard.user, qUser)
        .fetchJoin()
        .where(qBoard.title.contains(title))
        .fetchFirst();
  }
}
