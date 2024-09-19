package ru.volkovd.fatSecretParser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.repositories.CommonFoodCategoryRepository;

import java.util.List;

@Service
public class CommonFoodCategoryServiceImpl implements CommonFoodCategoryService {

    @Autowired
    private CommonFoodCategoryRepository commonFoodCategoryRepository;

    public CommonFoodCategoryServiceImpl(CommonFoodCategoryRepository commonFoodCategoryRepository) {
        this.commonFoodCategoryRepository = commonFoodCategoryRepository;
    }

    @Override
    public CommonFoodCategory save(String name, String url) {
        return commonFoodCategoryRepository.save(new CommonFoodCategory(name, url));
    }

    @Override
    public List<CommonFoodCategory> getAll() {
        return commonFoodCategoryRepository.findAll();
    }

    @Override
    public void delete(CommonFoodCategory commonFoodCategory) {
        commonFoodCategoryRepository.delete(commonFoodCategory);
    }
}
