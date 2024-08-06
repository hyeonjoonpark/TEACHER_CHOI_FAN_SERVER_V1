package bsm.choi.fancafe.domain.fcm.service;

import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TokenNotificationRequest;
import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TopicNotificationRequest;
import bsm.choi.fancafe.global.common.dto.response.ApiResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FcmService {
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> sendByToken(TokenNotificationRequest dto) {
        String token = "";

        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(dto.title())
                        .setBody(dto.content())
                        .setImage(dto.img())
                        .build())
                .putData("click_option", dto.url())
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ResponseEntity<ApiResponse> sendByTopic(TopicNotificationRequest dto) {
        Message message = Message.builder()
                .setTopic(dto.topic())
                .setNotification(Notification.builder()
                        .setTitle(dto.title())
                        .setBody(dto.content())
                        .setImage(dto.img())
                        .build())
                .putData("click_option", dto.url())
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
