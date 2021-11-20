package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.Brand;

public interface repoBrand extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT B FROM Brand B WHERE B.idBrand = ?1 ")
    Brand selectbrandwithid(long idBrand);

    @Query(value = "SELECT MAX(idBrand) FROM Brand ")
    long getMaxId();

    Brand findByBrandName(String brandname);
}