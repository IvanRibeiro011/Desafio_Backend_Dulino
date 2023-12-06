package com.dulino.desafio.entity;

import com.dulino.desafio.enums.WorkshopServiceStatus;
import com.dulino.desafio.enums.WorkshopServiceType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Document(collection = "services")
public class WorkshopTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private WorkshopServiceType serviceType;
    private LocalDateTime registeredAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private WorkshopServiceStatus serviceStatus;
    @DBRef(lazy = true)
    private Vehicle vehicle;
}
