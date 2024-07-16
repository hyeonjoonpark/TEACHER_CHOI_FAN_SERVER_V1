package bsm.choi.fancafe.domain.auth.presentation.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
  String token,
  String id,
  String email
) {

}
