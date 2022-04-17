package com.codecrew.personalitytest.restapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    public int statusCode;
    public Date timestamp;
    public String message;
    public String description;
}
