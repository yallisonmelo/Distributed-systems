package br.com.yfsmsystem.distributedsystems.producteast.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yfsmsystem.distributedsystems.producteast.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);
}
