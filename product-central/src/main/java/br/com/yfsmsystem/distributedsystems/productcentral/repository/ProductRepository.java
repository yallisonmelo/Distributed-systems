package br.com.yfsmsystem.distributedsystems.productcentral.repository;


import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
