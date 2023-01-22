package rent.calculator.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rent.calculator.com.model.entity.RentPrice;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RentPriceRepository extends CrudRepository<RentPrice, Long> {
    Optional<RentPrice> getByActualIsTrue();
    Set<RentPrice> getAllByActualIsFalse();
}
