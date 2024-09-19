package ru.volkovd.fatSecretParser;

import org.apache.commons.codec.DecoderException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.services.CommonFoodCategoryService;

import java.io.IOException;
import java.util.List;

@RestController
@SpringBootApplication
public class Application {

    private final CommonFoodCategoryParser commonFoodCategoryParser;
    private final CommonFoodCategoryService commonFoodCategoryService;
    private final FoodCategoryParser foodCategoryParser;
    private final BrandsParser brandsParser;

    public Application(CommonFoodCategoryParser commonFoodCategoryParser, CommonFoodCategoryService commonFoodCategoryService, FoodCategoryParser foodCategoryParser, BrandsParser brandsParser) {
        this.commonFoodCategoryParser = commonFoodCategoryParser;
        this.commonFoodCategoryService = commonFoodCategoryService;
        this.foodCategoryParser = foodCategoryParser;
        this.brandsParser = brandsParser;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public List<CommonFoodCategory> hello() throws IOException, DecoderException, InterruptedException {
        commonFoodCategoryParser.parse();
        //Thread.sleep(1000);
        //commonFoodCategoryParser.getCommons();
        //Thread.sleep(1000);
        //foodCategoryParser.getFoodCategories();
        //Thread.sleep(1000);
        //brandsParser.parse();
        return commonFoodCategoryService.getAll();
    }
}