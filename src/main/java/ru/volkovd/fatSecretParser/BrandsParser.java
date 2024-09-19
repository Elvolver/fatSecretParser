package ru.volkovd.fatSecretParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.volkovd.fatSecretParser.models.Brand;
import ru.volkovd.fatSecretParser.models.BrandType;
import ru.volkovd.fatSecretParser.repositories.BrandRepository;
import ru.volkovd.fatSecretParser.repositories.BrandTypeRepository;
import ru.volkovd.fatSecretParser.services.BrandService;
import ru.volkovd.fatSecretParser.services.BrandTypeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BrandsParser {

    private final Environment env;
    private String url;
    private Document document;
    private final BrandTypeService brandTypeService;
    private final BrandService brandService;

    public BrandsParser(Environment env, BrandTypeService brandTypeService, BrandRepository brandRepository, BrandService brandService) throws IOException {
        this.env = env;
        url = env.getProperty("base_url") + "/Default.aspx?pa=brands";
        this.brandTypeService = brandTypeService;
        this.brandService = brandService;
        document = Jsoup.connect(url).get();
    }

    private Map<String, String> getBrandTypes() {
        Map<String, String> brandTypes = new HashMap<>();

        Elements Elements = document.select("div.leftCellContent div.smallText a");
        for (Element element: Elements) {
            String href = element.attr("abs:href");

            Pattern pattern = Pattern.compile("&t=([^&]+)");
            Matcher matcher = pattern.matcher(href);

            String key = "";

            if (matcher.find()) {
                key = matcher.group(1);
            }

            String value = element.text();
            brandTypes.put(key, value);
        }
        return brandTypes;
    }

    public void saveBrandTypes(Map<String, String> brandTypes) {
        for (Map.Entry<String, String> entry : brandTypes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            brandTypeService.save(new BrandType(key, value));
        }
    }

    private List<String> getBrandDictionary() {
        List<String> DictionaryList = new ArrayList<>();
        Elements elements = document.select("div.leftCellContent div").get(3).select("a");
        for (Element element: elements) {
            DictionaryList.add(element.text());
        }
        return DictionaryList;
    }

    public void parse() throws IOException, InterruptedException {
        List<String> dictionaryList = getBrandDictionary();
        Map<String, String> brandTypesMap = getBrandTypes();
        List<String> brandTypes = new ArrayList<>(brandTypesMap.keySet());

        saveBrandTypes(brandTypesMap);

        for (String brandType: brandTypes) {
            for (String character: dictionaryList) {
                processCharacter(character, brandType);
                Thread.sleep(1000);
            }
        }

        System.out.println(brandTypes);
        System.out.println(dictionaryList);
    }

    private void processCharacter(String character, String brandType) throws IOException, InterruptedException {
        processCharacter(character, brandType, 0);
    }

    private List<String> processCharacter(String character, String brandTypeValue, Integer page) throws IOException, InterruptedException {
        String url = this.url + "&f=" + character + "&t=" + brandTypeValue + "&pg=" + page;
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.leftCellContent h2");
        BrandType brandType = brandTypeService.getByValue(brandTypeValue);

        List<String> brands = new ArrayList<>();

        for (Element element: elements) {
            String brandName = element.text();
            brands.add(brandName);

            brandService.save(new Brand(brandName, brandType));
        }

        if (elements.size() == 20) {
            Thread.sleep(1000);
            processCharacter(character, brandTypeValue, page + 1);
        }

        return brands;
    }
}
