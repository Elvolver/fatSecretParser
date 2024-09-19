package ru.volkovd.fatSecretParser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.models.FoodCategory;
import ru.volkovd.fatSecretParser.repositories.CommonFoodCategoryRepository;
import ru.volkovd.fatSecretParser.repositories.FoodCategoryRepository;

import java.util.List;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryRepository.save(foodCategory);
    }

    @Override
    public FoodCategory save(String name, String url, CommonFoodCategory commonFoodCategory) {
        return foodCategoryRepository.save(new FoodCategory(name, url, commonFoodCategory));
    }

    @Override
    public List<FoodCategory> getAll() {
        return foodCategoryRepository.findAll();
    }

    @Override
    public void delete(FoodCategory foodCategory) {
        foodCategoryRepository.delete(foodCategory);
    }
}
