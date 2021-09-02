package sit.int221.ppclothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.ppclothes.models.ReceiptDetails;

public interface repoReceiptDetails extends JpaRepository<ReceiptDetails,Long>{
}
