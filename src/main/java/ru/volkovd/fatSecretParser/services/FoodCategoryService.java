package ru.volkovd.fatSecretParser.services;

import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.models.FoodCategory;

import java.util.List;

public interface FoodCategoryService {
    FoodCategory save(FoodCategory foodCategory);
    FoodCategory save(String name, String url, CommonFoodCategory commonFoodCategory);
    List<FoodCategory> getAll();
    void delete(FoodCategory post);
}
