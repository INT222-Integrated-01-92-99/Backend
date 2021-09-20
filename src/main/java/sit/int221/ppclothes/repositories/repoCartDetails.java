package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.CartDetails;

import java.util.List;

public interface repoCartDetails extends JpaRepository<CartDetails,Long>{

    @Query(value = "SELECT cart.idCart FROM CartDetails where idCartDetail = ?1")
    Long getidcart(long idcartdetail);

    @Query(value = "SELECT c FROM CartDetails c where c.cart.idCart = ?1")
    List<CartDetails> listcartdetailByidcart(long idcart);

    @Query(value = "SELECT sum(proPerPiece) FROM CartDetails where product.idPro = ?2 and cart.idCart = ?1")
    Long getTotalInCart(long idcart,long idpro);

    @Query(value = "SELECT sum(proPerPiece) FROM CartDetails where product.idPro = ?2 and cart.idCart = ?1 and  idCartDetail <> ?3 ")
    Long getTotalInCartWithoutsomeId(long idcart,long idpro,long idcartdetail);
}
