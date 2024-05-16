package com.gamemarket.common.exception.like;

import lombok.Getter;

@Getter
public class LikeException extends RuntimeException {

    private final LikeExceptionCode code;

    public LikeException(final LikeExceptionCode code) {
        this.code = code;
    }

}
