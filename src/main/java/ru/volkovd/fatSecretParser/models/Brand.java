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
@SequenceGenerator(name = "brand_seq", sequenceName = "brand_id_seq", allocationSize = 1)
public class Brand {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "brand_seq")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="brand_type_id", referencedColumnName="id")
    private BrandType brandType;

    public Brand(String name, BrandType brandType) {
        this.name = name;
        this.brandType = brandType;
    }
}
