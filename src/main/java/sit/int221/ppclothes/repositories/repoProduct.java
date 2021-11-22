package sit.int221.ppclothes.repositories;

import org.springframework.data.repository.query.Param;
import sit.int221.ppclothes.models.Brand;
import sit.int221.ppclothes.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface repoProduct extends JpaRepository<Product,Long> {

    @Query(value = "SELECT Max(idPro) FROM Product")
    Long getMaxproId();

    Product findByProName(String proName);

    @Query(value = "SELECT P FROM Product P Where P.proName LIKE %?1% ")
    List<Product> searchwithoutbrand(String proName);

    @Query(value = "SELECT proAmount FROM Product Where idPro = ?1")
    long amount(long idpro);

    List<Product> findByBrand_IdBrand(long brand);
}
