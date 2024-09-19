package ru.volkovd.fatSecretParser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;

@Repository
public interface CommonFoodCategoryRepository extends JpaRepository<CommonFoodCategory, Long> {
}
