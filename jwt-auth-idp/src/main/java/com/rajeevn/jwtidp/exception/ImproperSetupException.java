package com.rajeevn.jwtidp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.PRECONDITION_FAILED,
        reason = "Your account has not been setup or not properly configured. Please verify or get in touch."
)
public class ImproperSetupException extends RuntimeException
{
}
