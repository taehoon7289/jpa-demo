package com.dev.blackmango.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLikeBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long no; // user_like_board PK
  @Column
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  private LocalDateTime regDate = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "user_no")
  private User user;

  @ManyToOne
  @JoinColumn(name = "board_no")
  private Board board;
}
