package ru.volkovd.fatSecretParser.services;

import org.springframework.stereotype.Service;
import ru.volkovd.fatSecretParser.models.BrandType;
import ru.volkovd.fatSecretParser.repositories.BrandTypeRepository;

import java.util.List;

@Service
public class BrandTypeServiceImpl implements BrandTypeService {

    private final BrandTypeRepository brandTypeRepository;

    public BrandTypeServiceImpl(BrandTypeRepository brandTypeRepository) {
        this.brandTypeRepository = brandTypeRepository;
    }

    @Override
    public BrandType save(BrandType brandType) {
        return brandTypeRepository.save(brandType);
    }

    @Override
    public BrandType getByValue(String value) {
        return brandTypeRepository.getByValue(value);
    }

    @Override
    public List<BrandType> getAll() {
        return brandTypeRepository.findAll();
    }

    @Override
    public void delete(BrandType brandType) {
        brandTypeRepository.delete(brandType);
    }
}
