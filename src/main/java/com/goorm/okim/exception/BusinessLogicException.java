package com.goorm.okim.exception;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@Builder
public class BusinessLogicException extends RuntimeException{
    private ErrorCodeMessage errorCodeMessage;
}
