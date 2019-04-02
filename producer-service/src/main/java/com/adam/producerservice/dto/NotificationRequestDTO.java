package com.adam.producerservice.dto;

/**
 * @author adambchouti on 2019-04-02.
 * @project producer-service
 */
public class NotificationRequestDTO {
    private String message;

    public NotificationRequestDTO() {
    }

    public NotificationRequestDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationRequestDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
