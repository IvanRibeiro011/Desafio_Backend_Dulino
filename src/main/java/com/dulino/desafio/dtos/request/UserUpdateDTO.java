package com.dulino.desafio.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank(message = "Campo requerido")
    private String firstName;
    @NotBlank(message = "Campo requerido")
    private String lastName;
    @NotBlank(message = "Campo requerido")
    @Size(min = 6, max = 12, message = "A senha precisa ter um tamanho válido")
    private String password;
    @NotEmpty(message = "Campo Requerido")
    private List<String> roleName;
}
