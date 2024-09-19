package ru.volkovd.fatSecretParser.services;

import org.springframework.stereotype.Service;
import ru.volkovd.fatSecretParser.models.Brand;
import ru.volkovd.fatSecretParser.repositories.BrandRepository;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }
}
