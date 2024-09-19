package ru.volkovd.fatSecretParser.services;

import ru.volkovd.fatSecretParser.models.Brand;
import ru.volkovd.fatSecretParser.models.BrandType;

import java.util.List;

public interface BrandService {
    Brand save(Brand brand);
    List<Brand> getAll();
    void delete(Brand brand);
}
