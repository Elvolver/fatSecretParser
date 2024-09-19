package ru.volkovd.fatSecretParser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.fatSecretParser.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
