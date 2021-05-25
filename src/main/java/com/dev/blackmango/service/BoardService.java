package com.dev.blackmango.service;

import com.dev.blackmango.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final UserRepository usersRepository;
}
