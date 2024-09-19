package ru.volkovd.fatSecretParser.services;

import ru.volkovd.fatSecretParser.models.BrandType;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.models.FoodCategory;

import java.util.List;

public interface BrandTypeService {
    BrandType save(BrandType brandType);
    BrandType getByValue(String value);
    List<BrandType> getAll();
    void delete(BrandType brandType);
}
