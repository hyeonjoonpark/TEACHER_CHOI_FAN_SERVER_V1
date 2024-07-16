package bsm.choi.fancafe.domain.auth.presentation.dto.request;

public record LoginRequest(
  String email,
  String password
) {

}
