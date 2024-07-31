package bsm.choi.fancafe.domain.notification.presentation.dto;

import bsm.choi.fancafe.domain.notification.types.ReadStatus;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.notification.Notification}
 */
@Builder
public record NotificationSendResponse(
        String senderName,
        String senderProfileImage,
        String message,
        ReadStatus isRead
) implements Serializable {

}