package com.capymenu.capy_menu_api.models.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseGeneric<T> {

    private String status;
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ResponseGeneric<T> of(HttpStatus status, String message, T data) {
        ResponseGeneric<T> response = new ResponseGeneric<>();
        response.status = status.getReasonPhrase();
        response.code = status.value();
        response.message = message;
        response.data = data;
        response.timestamp = LocalDateTime.now();
        return response;
    }

}
