package bsm.choi.fancafe.domain.notification.presentation.dto.response;

import bsm.choi.fancafe.domain.notification.types.ReadStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.notification.Notification}
 */
@Builder
public record NotificationReceivedResponse(
        String senderName,
        String senderProfileImage,
        String message,
        ReadStatus isRead
) implements Serializable {

}