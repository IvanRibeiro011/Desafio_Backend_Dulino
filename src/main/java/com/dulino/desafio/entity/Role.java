package com.dulino.desafio.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "authority")
@Document(collection = "roles")
public class Role implements Serializable , GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
