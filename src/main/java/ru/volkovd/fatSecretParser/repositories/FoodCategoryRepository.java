package ru.volkovd.fatSecretParser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.models.FoodCategory;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
