package bsm.choi.fancafe.domain.user.types;

public enum RoleType {
  STUDENT, // 학생
  TEACHER, // 선생님
  ADMIN; // 관리자

  private static final String PREFIX = "ROLE_";

  public String getUserRole() {
    return PREFIX + this.name();
  }
}
