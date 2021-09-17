package sit.int221.ppclothes.repositories;

import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.Cart;

public interface repoCart extends JpaRepository<Cart,Long>{

    @Query(value = "SELECT max(idCart) FROM Cart")
    Cart getMaxCartId();

//    @Query(value = "SELECT max(idCart) FROM Cart WHERE ")
//    Cart findByIdAcc(long idacc);

}
