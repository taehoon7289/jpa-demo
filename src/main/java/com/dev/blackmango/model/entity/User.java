package com.dev.blackmango.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long userNo; // user PK
  @Column(length = 50, nullable = false)
  private String id; // 아이디
  @Column(length = 256, nullable = false)
  private String password; // 비밀번호
  @Column(length = 50)
  private String name; // 이름
  @Column
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  private LocalDateTime regDate = LocalDateTime.now(); // 가입일

  @OneToMany(mappedBy = "user")
  private List<Board> boards;
}
