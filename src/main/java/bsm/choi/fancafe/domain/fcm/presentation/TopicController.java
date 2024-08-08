package bsm.choi.fancafe.domain.fcm.presentation;

import bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust.TopicRequest;
import bsm.choi.fancafe.domain.fcm.service.FcmService;
import bsm.choi.fancafe.global.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fcm/topic", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class TopicController {
    private final FcmService fcmService;

    @PostMapping("/subscribe")
    public ResponseEntity<ApiResponse> subscribe(@RequestBody TopicRequest dto) {
        fcmService.subScribeToTopic(dto.topic());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .successStatus(HttpStatus.OK)
                .successContent(dto.topic() + "토픽을 구독하였습니다")
                .build());
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<ApiResponse> unsubscribe(@RequestBody TopicRequest dto) {
        fcmService.unsubscribeFromTopic(dto.topic());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .successStatus(HttpStatus.OK)
                .successContent(dto.topic() + "토픽 구독 취소합니다")
                .build());
    }

}
