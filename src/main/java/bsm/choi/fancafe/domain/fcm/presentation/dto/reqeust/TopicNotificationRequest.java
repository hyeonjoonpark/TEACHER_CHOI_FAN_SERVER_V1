package bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record TopicNotificationRequest(
        @NotBlank String title,
        @NotBlank String content,
        String url,
        String img,
        String topic
) implements Serializable {

}
