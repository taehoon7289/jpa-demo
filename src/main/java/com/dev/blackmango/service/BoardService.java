package com.dev.blackmango.service;

import com.dev.blackmango.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardService {

  private UserRepository usersRepository;
}
