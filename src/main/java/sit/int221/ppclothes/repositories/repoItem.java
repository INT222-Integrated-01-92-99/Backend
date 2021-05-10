package sit.int221.ppclothes.repositories;

import sit.int221.ppclothes.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repoItem extends JpaRepository<Item,Long> {
}