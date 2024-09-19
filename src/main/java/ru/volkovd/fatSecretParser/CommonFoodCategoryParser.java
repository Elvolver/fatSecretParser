package ru.volkovd.fatSecretParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.volkovd.fatSecretParser.services.CommonFoodCategoryService;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
public class CommonFoodCategoryParser {
    private Document document;

    private final CommonFoodCategoryService commonFoodCategoryService;

    public CommonFoodCategoryParser(CommonFoodCategoryService commonFoodCategoryService) {
        this.commonFoodCategoryService = commonFoodCategoryService;
    }

    public void parse() throws IOException {
        String url = "калории-питание/";
        url = "http://www.fatsecret.ru/" + URLEncoder.encode(url, "windows-1251");
        document = Jsoup.connect(url).get();
    }

    public Elements getCommons() throws IOException {

        Elements commons = document.select("table.common td.borderBottom div.details a.prominent");


        for (Element common : commons) {
            String commonName = new String(common.text().getBytes("UTF-8"), "Windows-1251");
            String url = common.attr("abs:href");
            String decodedUrl = URLDecoder.decode(url, "UTF-8");
            System.out.println(commonName + " " + decodedUrl);
            System.out.println(new String(common.attr("abs:href").getBytes("UTF-8"), "Windows-1251"));
            commonFoodCategoryService.save(common.text(), decodedUrl);
        }

        return document.select("table.common div.details a.prominent");
    }
}
