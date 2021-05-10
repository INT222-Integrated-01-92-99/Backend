package sit.int221.ppclothes.repositories;

import sit.int221.ppclothes.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface repoProduct extends JpaRepository<Product,Long> {

    @Query(value = "SELECT Max(idPro) FROM Product")
    Long getMaxproId();

    @Query(value = "SELECT proName FROM Product Where proName = ?1")
    Product findByName(String proName);
}
