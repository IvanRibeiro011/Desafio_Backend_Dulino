package com.dulino.desafio.handler.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FieldError {

    private String fieldName;
    private String message;
}
