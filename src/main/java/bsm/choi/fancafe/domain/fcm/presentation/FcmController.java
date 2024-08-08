package bsm.choi.fancafe.domain.fcm.presentation;

import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TokenNotificationRequest;
import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TopicNotificationRequest;
import bsm.choi.fancafe.domain.fcm.service.FcmService;
import bsm.choi.fancafe.global.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fcm", produces = "application/json; charset=utf8")
public class FcmController {
    private final FcmService fcmService;

    @PostMapping("/notification/token")
    public ResponseEntity<ApiResponse> sendMessageToken(@RequestBody TokenNotificationRequest dto) {
        return fcmService.sendByToken(dto);
    }

    @PostMapping("/notification/topic")
    public ResponseEntity<ApiResponse> sendMessageTopic(@RequestBody TopicNotificationRequest dto) {
        return fcmService.sendByTopic(dto);
    }
}
