package ma.projet.tp_jpa.repositories;

import ma.projet.tp_jpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String keyword);
    List<Product> findByPriceLessThan(double price);
}
