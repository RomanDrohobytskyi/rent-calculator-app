package rent.calculator.com.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rent.calculator.com.model.entity.Payment;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PaymentEntityRepository extends CrudRepository<Payment, Long> {
    Set<Payment> findAll();
    Optional<Payment> findByPaymentDateBetween(LocalDate from, LocalDate to);
}
