package ru.volkovd.fatSecretParser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.fatSecretParser.models.BrandType;
import ru.volkovd.fatSecretParser.models.FoodCategory;

@Repository
public interface BrandTypeRepository extends JpaRepository<BrandType, Long> {
    BrandType getByValue(String value);
}
