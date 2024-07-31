package bsm.choi.fancafe.domain.notification.service;

import bsm.choi.fancafe.domain.notification.Notification;
import bsm.choi.fancafe.domain.notification.presentation.dto.NotificationSendResponse;
import bsm.choi.fancafe.domain.notification.presentation.dto.request.NotificationRequest;
import bsm.choi.fancafe.domain.notification.presentation.dto.response.NotificationReceivedResponse;
import bsm.choi.fancafe.domain.notification.repository.NotificationRepository;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    // 내가 받은 알림 조회
    @Transactional(
            readOnly = true,
            rollbackFor = Exception.class
    )
    public List<NotificationReceivedResponse> receivedResponses(String uuid) {
        List<Notification> notifications = notificationRepository.findByReceiverUuid(UUID.fromString(uuid));

        // Notification 객체를 NotificationReceivedResponse 객체로 변환
        return notifications.stream()
                .map(notification -> NotificationReceivedResponse.builder()
                        .senderName(notification.getReceiver().getName())
                        .senderProfileImage(notification.getReceiver().getProfileImage())
                        .message(notification.getMessage())
                        .isRead(notification.getIsRead())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Transactional(
            readOnly = true,
            rollbackFor = Exception.class
    )
    public List<NotificationSendResponse> sendResponses(String uuid) {
        List<Notification> notifications = notificationRepository.findBySenderUuid(UUID.fromString(uuid));

        // Notification 객체를 NotificationReceivedResponse 객체로 변환
        return notifications.stream()
                .map(notification -> NotificationSendResponse.builder()
                        .senderName(notification.getSender().getName())
                        .senderProfileImage(notification.getSender().getProfileImage())
                        .message(notification.getMessage())
                        .isRead(notification.getIsRead())
                        .build()
                )
                .collect(Collectors.toList());
    }


    @Transactional(rollbackFor = Exception.class)
    public void sendNotification(NotificationRequest dto) {

        System.out.println("dto.sender() = " + dto.sender());
        System.out.println("dto.receiverEmail() = " + dto.receiverEmail());
        System.out.println("dto.message() = " + dto.message());

        User sender = userRepository.findByUuid(UUID.fromString(dto.sender()))
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        System.out.println("sender = " + sender);

        User receiver = userRepository.findByEmail(dto.receiverEmail())
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        System.out.println("receiver = " + receiver);

        // Notification 객체 생성
        Notification notification = Notification.builder()
                .receiver(receiver)
                .sender(sender)
                .message(dto.message())
                .build();

        System.out.println("notification = " + notification);

        // Notification 추가
        sender.addSendNotification(notification);
    }

}
