package bsm.choi.fancafe.domain.notification.presentation;

import bsm.choi.fancafe.domain.notification.presentation.dto.NotificationSendResponse;
import bsm.choi.fancafe.domain.notification.presentation.dto.request.NotificationRequest;
import bsm.choi.fancafe.domain.notification.presentation.dto.response.NotificationReceivedResponse;
import bsm.choi.fancafe.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notification", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/receive/list")
    public List<NotificationReceivedResponse> getReceived(@RequestParam String uuid) {
        return notificationService.receivedResponses(uuid);
    }

    @GetMapping("/send/list")
    public List<NotificationSendResponse> getSent(@RequestParam String uuid) {
        return notificationService.sendResponses(uuid);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(
            @RequestBody NotificationRequest notificationRequest
    ) {
        notificationService.sendNotification(notificationRequest);
        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 알림을 보냈습니다");
    }
}
