package bsm.choi.fancafe.domain.notification.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder
public record NotificationRequest(
        @JsonProperty("senderId")
        String sender,
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "유효한 Gmail 주소를 입력하세요.")
        String receiverEmail,
        @Size(min = 2, message = "알림은 최소 2글자 이상이여야 합니다")
        String message
) {

}
