package bsm.choi.fancafe.domain.user.types;

public enum RoleType {
  USER, // 일반 사용자
  ADMIN; // 관리자

  private static final String PREFIX = "ROLE_";

  public String getUserRole() {
    return PREFIX + this.name();
  }
}
