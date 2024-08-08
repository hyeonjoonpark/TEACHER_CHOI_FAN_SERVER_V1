package bsm.choi.fancafe.domain.fcm.presentation.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;

@Builder
public record TopicRequest(
        @JsonCreator String topic
) {

}
