package com.dulino.desafio.entity.embedded;

import com.dulino.desafio.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Owner {

    private String id;
    private String fullName;

    public Owner(User entity) {
        this.id = entity.getId();
        this.fullName = entity.getFirstName() + " " + entity.getLastName();
    }

}
