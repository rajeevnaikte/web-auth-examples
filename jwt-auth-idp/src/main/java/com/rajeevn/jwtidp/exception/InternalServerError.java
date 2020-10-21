package com.rajeevn.jwtidp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.INTERNAL_SERVER_ERROR,
        reason = "An unexpected technical error has occurred. Will investigate. Please feel free to contact us."
)
public class InternalServerError extends RuntimeException
{
    public InternalServerError(Throwable cause)
    {
        super(cause);
    }
}
