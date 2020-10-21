package com.rajeevn.jwtidp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Invalid request data. Please verify the data being sent."
)
public class BadRequest extends RuntimeException
{
}
