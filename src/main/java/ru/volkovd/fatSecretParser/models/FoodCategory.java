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
@SequenceGenerator(name = "food_category_seq", sequenceName = "food_category_id_seq", allocationSize = 1)
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "food_category_seq")
    private Long id;

    private String name;

    @Column(length = 512)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="common_food_category_id", referencedColumnName="id")
    private CommonFoodCategory commonFoodCategory;

    public FoodCategory(String name, String url, CommonFoodCategory commonFoodCategory) {
        this.name = name;
        this.url = url;
        this.commonFoodCategory = commonFoodCategory;
    }
}
