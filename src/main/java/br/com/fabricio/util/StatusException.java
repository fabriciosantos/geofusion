package br.com.fabricio.util;

import javax.ws.rs.core.Response.Status;

public class StatusException extends Exception {

    private static final long serialVersionUID = -5121230815249903470L;
    private int status;

    public StatusException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Status getStatusType() {
        switch (getStatus()) {
        case 400:
            return Status.BAD_REQUEST;
        case 401:
            return Status.UNAUTHORIZED;
        case 404:
            return Status.NOT_FOUND;
        default:
            return Status.INTERNAL_SERVER_ERROR;
        }
    }
}
