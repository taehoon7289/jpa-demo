package com.dev.blackmango.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long boardNo; // board PK
  @Column(length = 50, nullable = false)
  private String title; // 제목
  @Column(length = 256, nullable = false)
  private String contents; // 내용

  @Column
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") // ISO-8601 formatting
  private LocalDateTime regDate = LocalDateTime.now(); // "작성일"

  @ManyToOne
  @JoinColumn(name = "user_no", nullable = false)
  private User user;

}
