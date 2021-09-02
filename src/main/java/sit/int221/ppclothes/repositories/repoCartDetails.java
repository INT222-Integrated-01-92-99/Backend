package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.CartDetails;

public interface repoCartDetails extends JpaRepository<CartDetails,Long>{
}
