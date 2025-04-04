package org.desafiocresol.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final Response.Status status;

    public AppException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }
}
