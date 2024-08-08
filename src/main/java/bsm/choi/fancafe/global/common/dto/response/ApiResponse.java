package bsm.choi.fancafe.global.common.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ApiResponse(
        HttpStatus successStatus,
        String successContent
) {

}
