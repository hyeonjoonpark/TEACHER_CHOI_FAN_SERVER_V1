package bsm.choi.fancafe.domain.fcm.service;

import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TokenNotificationRequest;
import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TopicNotificationRequest;
import bsm.choi.fancafe.global.common.dto.response.ApiResponse;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    public void subScribeToTopic(String topicName) {
        List<String> failedTokens = new ArrayList<>();
        List<String> tokens = new ArrayList<>();

        tokens.add("[대충 여기 토큰 넣음]");

        try {
            TopicManagementResponse response = FirebaseMessaging
                    .getInstance()
                    .subscribeToTopicAsync(tokens, topicName).get();
            log.info("구독하는 토픽 : {}", topicName);
            log.info(response.getSuccessCount() + "개 토큰이 구독에 성공하였습니다.");

            if(response.getFailureCount() > 0) {
                log.info(response.getFailureCount() + "개 토큰이 구독에 실패하였습니다.");
                response.getErrors().forEach(
                        error -> {
                            failedTokens.add(tokens.get(error.getIndex()));
                            log.info("Error for token index " + error.getIndex() + ": " + error.getReason() +
                                            "(Token : " + failedTokens.get(error.getIndex() - 1) + ")");
                        }
                );
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("구독을 실패 하였습니다");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void unsubscribeFromTopic(String topic) {
        List<String> failedTokens = new ArrayList<>();
        List<String> tokens = new ArrayList<>();

        tokens.add("[대충 여기 토큰 넣음]");

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance()
                    .unsubscribeFromTopicAsync(tokens, topic).get();

            log.info("구독 취소하는 토픽 : {}", topic);
            log.info(response.getSuccessCount() + "개 토큰이 구독취소를 성공하였습니다.");

            if(response.getFailureCount() > 0) {
                log.info(response.getFailureCount() + "개 토큰이 구독취소를 실패하였습니다.");
                response.getErrors().forEach(
                        error -> {
                            failedTokens.add(tokens.get(error.getIndex()));
                            log.info("Error for token index " + error.getIndex() + ": " + error.getReason() +
                                    "(Token : " + failedTokens.get(error.getIndex() - 1) + ")");
                        }
                );
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("구독 취소를 실패하였습니다");
        }
    }

}
