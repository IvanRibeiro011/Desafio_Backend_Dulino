package com.dulino.desafio.handler.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationError extends ApiErrorMessage {

    private List<FieldError> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        errors.add(new FieldError(fieldName, message));
    }
}
