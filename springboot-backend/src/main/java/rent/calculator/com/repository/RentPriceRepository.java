package rent.calculator.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rent.calculator.com.model.entity.RentPrice;

@Repository
public interface RentPriceRepository extends CrudRepository<RentPrice, Long> {
    RentPrice getByActual(boolean actual);
}
