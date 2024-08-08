package bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record TokenNotificationRequest(
        @NotBlank String title,
        @NotBlank String content,
        String url,
        String img
) implements Serializable {

}
