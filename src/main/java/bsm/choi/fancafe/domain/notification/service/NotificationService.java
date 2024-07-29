package bsm.choi.fancafe.domain.notification.service;

import bsm.choi.fancafe.domain.notification.Notification;
import bsm.choi.fancafe.domain.notification.presentation.dto.request.NotificationRequest;
import bsm.choi.fancafe.domain.notification.repository.NotificationRepository;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;

    @Transactional
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
