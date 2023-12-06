package com.dulino.desafio.dtos.response;

import com.dulino.desafio.entity.Role;
import com.dulino.desafio.entity.User;
import com.dulino.desafio.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<VehicleDTO> vehicles = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    public UserDTO(User entity, List<Vehicle> vehicles) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        for (Vehicle v : vehicles) {
            this.vehicles.add(new VehicleDTO(v, v.getTasks()));
        }
        this.roles.addAll(entity.getRoles());
    }

}
