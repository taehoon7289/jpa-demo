package com.dev.blackmango.service;

import com.dev.blackmango.common.component.JwtTokenProvider;
import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.exception.code.UserExCode;
import com.dev.blackmango.model.dto.JWTDataDTO;
import com.dev.blackmango.model.dto.SignInDTO;
import com.dev.blackmango.model.dto.SignUpDTO;
import com.dev.blackmango.model.entity.User;
import com.dev.blackmango.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;
  private JwtTokenProvider jwtTokenProvider;
  private PasswordEncoder passwordEncoder;

  public List<User> getListUsers() {
    return userRepository.findAll();
  }

  public User getUsersById(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseGet(() -> null);
  }

  public User getUsersByName(String name) {
    Optional<User> user = userRepository.findByName(name);
    return user.orElseGet(() -> null);
  }

  public List<User> getUsersByNameLike(String name) {
    return userRepository.findByNameLike(name);
  }

  public User getUsersByIdAndName(String id, String name) {
    Optional<User> user = userRepository.findByIdAndName(id, name);
    return user.orElseGet(() -> null);
  }

  public Page<User> getUsersPage(int page, int limit) {
    // paging 처리, sort 관련 방법
    Pageable pageable = PageRequest
        .of(page, limit, Sort.by("id").descending().and(Sort.by("userNo").ascending()));
    return userRepository.findAll(pageable);
  }

  public Page<User> getUsersPageByIdAndName(int page, int limit, String id, String name) {
    Pageable pageable = PageRequest.of(page, limit);
    return userRepository.findPageByIdAndName(id, name, pageable);
  }

  @Transactional
  public Map<String, Object> signIn(SignInDTO signInDTO, HttpServletRequest request,
      HttpServletResponse response) throws ServiceException {
    String id = signInDTO.getId();
    String password = signInDTO.getPassword();
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(UserExCode.INVALID_ID));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new ServiceException(UserExCode.INVALID_PASSWORD);
    }
    // token 발급
    JWTDataDTO jwtDataDTO = JWTDataDTO.builder()
        .userNo(user.getUserNo())
        .id(user.getId())
        .name(user.getName())
        .build();
    response.setHeader(HttpHeaders.AUTHORIZATION, this.getAccessToken(jwtDataDTO));
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("user", user);
    resultMap.put("board", user.getBoards());
    return resultMap;
  }

  @Transactional
  public Map<String, Object> signUp(SignUpDTO signUpDTO, HttpServletRequest request,
      HttpServletResponse response) throws ServiceException {
    User user = User.builder()
        .id(signUpDTO.getId())
        .password(passwordEncoder.encode(signUpDTO.getPassword()))
        .name(signUpDTO.getName())
        .regDate(LocalDateTime.now())
        .build();
    User rUser = userRepository.save(user);
    // token 발급
    JWTDataDTO jwtDataDTO = JWTDataDTO.builder()
        .userNo(rUser.getUserNo())
        .id(rUser.getId())
        .name(rUser.getName())
        .build();
    response.setHeader(HttpHeaders.AUTHORIZATION, this.getAccessToken(jwtDataDTO));
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("user", rUser);
    return resultMap;
  }

  public String getAccessToken(JWTDataDTO jwtDataDTO) {
    return jwtTokenProvider
        .createToken("access_token", new ObjectMapper().convertValue(jwtDataDTO, Map.class));
  }

}
