package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.Account;
import sit.int221.ppclothes.models.AuthenticationUser;

public interface repoAccount extends JpaRepository<Account,Long>{

    @Query(value = "SELECT cart FROM Account WHERE idAccount = ?1")
    Long getidcart(long idacc);

    @Query(value = "SELECT a.idAccount FROM Account a ,Cart c where a.cart.idCart = c.idCart and c.idCart = ?1")
    Long getidacc(long idcart);

    @Query(value = "SELECT MAX(idAccount) FROM Account ")
    Long getMaxidAcc();

    Account findByAccUsername(String username);

}
