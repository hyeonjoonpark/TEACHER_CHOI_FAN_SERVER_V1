package bsm.choi.fancafe.domain.user.utils;

import bsm.choi.fancafe.domain.user.RoleType;
import bsm.choi.fancafe.domain.user.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter { // 안 보내는 요청에 대해서도 허용하면 안되기 때문

  private final UserService userService;
  private final String secretKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    // header에서 authentication을 꺼냄
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization: {}", authorization);

    // token을 안 보내면 Block
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      // authorization이 null 이면 권한부여 안함
      // token 앞에 Bearer 붙여서 보냈는지 확인
      log.error("authorization을 잘못 보냈습니다");
      filterChain.doFilter(request, response);
      return;
    }

    // 토큰 꺼내기
    String token = authorization.split(" ")[1];

    // Token Expired(만료) 되었는지 확인
    if (JwtUtil.isExpired(token, secretKey)) {
      log.error("token이 만료되었습니다");
      filterChain.doFilter(request, response);
      return;
    }

    // UserId 토큰에서 꺼내기
    String userId = JwtUtil.getUserId(token, secretKey);
    log.info("userId : {}", userId);

    // 권환부여
    UsernamePasswordAuthenticationToken authenticationToken =
      new UsernamePasswordAuthenticationToken(
        userId,
        null,
        List.of(
          new SimpleGrantedAuthority(RoleType.USER.userRole())
        )
      );

    // Detail을 넣어줍니다
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    filterChain.doFilter(request, response);
  }
}
