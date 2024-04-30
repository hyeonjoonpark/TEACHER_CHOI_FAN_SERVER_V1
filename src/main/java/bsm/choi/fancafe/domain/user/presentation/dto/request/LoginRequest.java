package bsm.choi.fancafe.domain.user.presentation.dto.request;

public record LoginRequest(
  String id,
  String email,
  String password
) {

}
