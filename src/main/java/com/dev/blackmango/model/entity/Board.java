package com.dev.blackmango.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long boardNo; // board PK
  @Column(length = 50, nullable = false)
  private String title; // 제목
  @Column(columnDefinition = "text", nullable = false)
  private String contents; // 내용

  @Column
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") // ISO-8601 formatting
  private LocalDateTime regDate = LocalDateTime.now(); // "작성일"
  @Column
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") // ISO-8601 formatting
  private LocalDateTime uptDate = LocalDateTime.now(); // "수정일"

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_no", nullable = false)
  @JsonIgnore
  private User user;

}
