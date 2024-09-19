package ru.volkovd.fatSecretParser.services;

import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import java.util.List;

public interface CommonFoodCategoryService {
    CommonFoodCategory save(String name, String url);

    List<CommonFoodCategory> getAll();

    void delete(CommonFoodCategory post);
}
