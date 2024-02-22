package bsm.choi.fancafe.domain.user;

public enum RoleType {
  USER, // 일반 사용자
  MANAGER, // 관리자
  VIP; // 특별회원

  private static final String PREFIX = "ROLE_";

  public String userRole() {
    return PREFIX + this.name();
  }
}
