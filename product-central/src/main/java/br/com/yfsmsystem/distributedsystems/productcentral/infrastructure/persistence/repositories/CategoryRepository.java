package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.CategoryEntity;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  boolean existsByDescription(String description);

  List<CategoryEntity> findAllByActive(Boolean status);
}
