package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.Receipt;

import java.util.List;

public interface repoReceipt extends JpaRepository<Receipt,Long>{
    List<Receipt> findByAccount_IdAccount(long id);
}