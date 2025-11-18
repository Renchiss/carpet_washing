package ru.rrishbuldin.carpet_washing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import ru.rrishbuldin.carpet_washing.security.enums.RoleName;
import java.util.UUID;

    @Getter
    @Setter
    @Entity
    @Table(name = "role")
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @NotNull
        @ColumnDefault("gen_random_uuid()")
        @Column(name = "object_id", nullable = false)
        private UUID objectId;

        @Size(max = 50)
        @NotNull
        @Column(name = "name", nullable = false, length = 50)
        @Enumerated(EnumType.STRING)
        private RoleName name;

        @Size(max = 255)
        @NotNull
        @Column(name = "description", nullable = false)
        private String description;

        //TODO возможно сделать таблицу прав в базе для динамики
    }