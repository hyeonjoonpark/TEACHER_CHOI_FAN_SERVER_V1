package bsm.choi.fancafe.global.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  // USER
  BAD_REQUEST_AUTH(400, "USER-400-1", "인증 요청정보가 잘못되었습니다"),
  USER_NOT_LOGIN(403, "USER-403-1", "로그인이 되어있지 않은 사용자입니다"),
  USER_NOT_FOUND(404, "USER-404-1", "사용자를 찾을 수 없습니다"),
  TOKEN_EXPIRED(404, "USER-404-2", "토큰을 찾을 수 없습니다"),
  USER_ALREADY_EXIST(409, "USER-409-1", "이미 존재하는 사용자입니다"),

  // BOARD
  BOARD_NOT_FOUND(404, "BOARD-404-1", "게시물을 찾을 수 없습니다"),

  // SERVER
  INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "내부 서버 오류가 발생했습니다");

  private final int status;
  private final String code;
  private final String message;
}
