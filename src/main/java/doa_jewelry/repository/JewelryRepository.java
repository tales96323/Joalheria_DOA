package doa_jewelry.repository;

import doa_jewelry.entity.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {

    Optional<Jewelry> findByName(String name);

    @Query("SELECT j FROM Jewelry j WHERE j.stockQuantity < :quantity")
    List<Jewelry> findOutOfStockJewelry(@Param("quantity") int quantity);
}
