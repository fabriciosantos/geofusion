package br.com.fabricio.util;

import java.io.Serializable;

public class FailureReason implements Serializable{

    private static final long serialVersionUID = -153886528854988136L;

    private String failureReason;

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

}
