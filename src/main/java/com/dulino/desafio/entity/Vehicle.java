package com.dulino.desafio.entity;

import com.dulino.desafio.entity.embedded.Owner;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Document(collection = "vehicles")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Indexed(unique = true)
    private String plate;
    private String model;
    private Integer year;

    private Owner owner;
    @DBRef(lazy = true)
    private List<WorkshopTask> tasks = new ArrayList<>();

}
