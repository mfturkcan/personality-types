package com.codecrew.personalitytest.restapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public record ErrorMessage(int statusCode, Date timestamp, String message, String description) {
}
