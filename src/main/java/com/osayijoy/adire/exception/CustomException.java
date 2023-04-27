package com.osayijoy.adire.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public CustomException( String message) {
        this.message = message;
    }
    public CustomException( String message,HttpStatus status) {
        this.status = status;
        this.message = message;
    }

}
