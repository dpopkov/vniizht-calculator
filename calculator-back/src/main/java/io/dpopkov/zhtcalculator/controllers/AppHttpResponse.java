package io.dpopkov.zhtcalculator.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class AppHttpResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date timestamp = new Date();
    private final HttpStatus httpStatus;
    private final int httpStatusCode;
    private final String reason;
    private final String message;

    public AppHttpResponse(HttpStatus httpStatus, String message) {
        this(httpStatus, httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }

    public AppHttpResponse(HttpStatus httpStatus, int httpStatusCode, String reason, String message) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
        this.reason = reason.toUpperCase();
        this.message = message;
    }
}
