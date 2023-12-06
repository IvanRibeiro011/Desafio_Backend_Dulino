package com.dulino.desafio.handler.messages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiErrorMessage {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
