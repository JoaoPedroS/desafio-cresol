package org.desafiocresol.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Response.Status status;

        if (exception instanceof AppException appEx) {
            status = appEx.getStatus();
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }

        return Response.status(status)
                .entity(new ErrorResponse(status.getReasonPhrase(), exception.getMessage()))
                .build();
    }

    public static class ErrorResponse {
        public String message;
        public String details;

        public ErrorResponse(String message, String details) {
            this.message = message;
            this.details = details;
        }
    }
}
