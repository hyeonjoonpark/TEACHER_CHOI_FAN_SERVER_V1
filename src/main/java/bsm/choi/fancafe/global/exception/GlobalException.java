package bsm.choi.fancafe.global.exception;

import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;
}