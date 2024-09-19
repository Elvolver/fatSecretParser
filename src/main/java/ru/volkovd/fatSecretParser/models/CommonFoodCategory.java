package ru.volkovd.fatSecretParser.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "common_food_category_seq", sequenceName = "common_food_category_id_seq", allocationSize = 1)
public class CommonFoodCategory {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "common_food_category_seq")
    private Long id;
    private String name;
    @Column(length = 512)
    private String url;

    public CommonFoodCategory(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
