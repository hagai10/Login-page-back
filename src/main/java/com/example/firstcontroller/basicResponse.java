package com.example.firstcontroller;

public class basicResponse {
    private boolean success;
    private Integer errorCode;
    private User user;

    public basicResponse(boolean success,Integer error) {
        this.success = success;
        this.errorCode = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public basicResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}



