package com.goorm.okim.exception.auth;

import com.goorm.okim.exception.ErrorCodeMessage;
import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class EmailNotFoundException extends UsernameNotFoundException {
    private final ErrorCodeMessage errorCodeMessage;

    public EmailNotFoundException(ErrorCodeMessage errorCodeMessage) {
        super(errorCodeMessage.getMessage());
        this.errorCodeMessage = errorCodeMessage;
    }
}
