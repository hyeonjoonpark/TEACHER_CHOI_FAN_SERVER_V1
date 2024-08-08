package bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record TopicRequest(
        String topic
) implements Serializable {

}
