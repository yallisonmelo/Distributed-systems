package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  Boolean existsProductEntityByName(String name);
}
