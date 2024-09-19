package ru.volkovd.fatSecretParser.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "brand_type_seq", sequenceName = "brand_type_id_seq", allocationSize = 1)
public class BrandType {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "brand_type_seq")
    private Long id;
    private String name;
    private String value;

    public BrandType(String value, String name) {
        this.name = name;
        this.value = value;
    }
}
