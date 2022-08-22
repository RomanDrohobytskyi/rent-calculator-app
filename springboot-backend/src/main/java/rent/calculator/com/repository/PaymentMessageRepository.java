package rent.calculator.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rent.calculator.com.model.entity.PaymentMessage;

@Repository
public interface PaymentMessageRepository extends CrudRepository<PaymentMessage, Long> {
    PaymentMessage getByActual(boolean actual);
}
