package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.Account;

public interface repoAccount extends JpaRepository<Account,Long>{

    @Query(value = "SELECT cart FROM Account WHERE idAccount = ?1")
    Long getidcart(long idacc);

}
