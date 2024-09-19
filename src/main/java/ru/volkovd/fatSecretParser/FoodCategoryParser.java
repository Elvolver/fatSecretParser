package ru.volkovd.fatSecretParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.volkovd.fatSecretParser.models.CommonFoodCategory;
import ru.volkovd.fatSecretParser.models.FoodCategory;
import ru.volkovd.fatSecretParser.services.CommonFoodCategoryService;
import ru.volkovd.fatSecretParser.services.FoodCategoryService;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@Component
public class FoodCategoryParser {

    private final CommonFoodCategoryService commonFoodCategoryService;
    private final FoodCategoryService foodCategoryService;

    public FoodCategoryParser(CommonFoodCategoryService commonFoodCategoryService, FoodCategoryService foodCategoryService) {
        this.commonFoodCategoryService = commonFoodCategoryService;
        this.foodCategoryService = foodCategoryService;
    }

    public void getFoodCategories() throws IOException, InterruptedException {
        List<CommonFoodCategory> commonFoodsCategories = commonFoodCategoryService.getAll();
        for (CommonFoodCategory commonFoodCategory : commonFoodsCategories) {
            String url = commonFoodCategory.getUrl();
            Document document = Jsoup.connect(url).get();
            Thread.sleep(1000);
            Elements foodCategory = document.select("div.secHolder h2");
            Elements mores = document.select("div.secHolder div.more");
            for (int i = 0; i < mores.size(); i++) {
                Element more = mores.select("a").get(i);
                url = more.attr("abs:href");
                String name = new String(foodCategory.get(i).text().getBytes("UTF-8"), "Windows-1251");
                System.out.println(name + " " + url);
                String decodedUrl = URLDecoder.decode(url, "UTF-8");
                FoodCategory fc = new FoodCategory(foodCategory.get(i).text(), decodedUrl, commonFoodCategory);
                System.out.println(fc);
                foodCategoryService.save(fc);
            }
            System.out.println("");
        }
    }

}
