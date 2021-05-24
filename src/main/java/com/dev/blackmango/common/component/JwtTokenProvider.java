package com.dev.blackmango.common.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

  @Value("${spring.jwt.valid-millisecond}")
  private long validMillisecond;
  @Value("${spring.jwt.secret-key}")
  private String secretKey;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // Jwt 토큰 생성
  public String createToken(String type, Map<String, Object> data) {
    Claims claims = Jwts.claims().setSubject(type);
    claims.put("data", data);
    Date now = new Date();
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setClaims(claims) // 데이터
        .setIssuedAt(now) // 토큰 발행일자
        .setExpiration(new Date(now.getTime() + validMillisecond)) // set Expire Time
        .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
        .compact();
  }

  // Jwt 토큰 타입
  public String getTokenSubject(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  // Jwt 토큰의 유효성 + 만료일자 확인
  public boolean validateToken(String jwtToken) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  // Jwt Data 가져오기
  public Map<String, Object> getData(String token) {
    return (Map) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("data");
  }
}
