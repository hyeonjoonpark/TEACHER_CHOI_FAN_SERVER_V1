package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import lombok.Builder;

@Builder
public record LoginRequest(
  String email,
  String password
) {

}
