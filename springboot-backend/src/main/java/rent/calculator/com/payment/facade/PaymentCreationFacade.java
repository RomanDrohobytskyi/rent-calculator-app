package rent.calculator.com.payment.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.payment.exception.PaymentDeleteException;
import rent.calculator.com.payment.repository.PaymentEntityRepository;
import rent.calculator.com.service.PaymentCreationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCreationFacade {
    private final PaymentEntityRepository paymentEntityRepository;
    private final PaymentCreationService paymentCreationService;

    public PaymentDTO save(PaymentDTO paymentDTO) {
        return paymentCreationService.save(paymentDTO);
    }

    public PaymentDTO recalculate(PaymentDTO paymentDTO) {
        return paymentCreationService.recalculate(paymentDTO);
    }

    public void delete(Long id) {
        try {
            paymentEntityRepository.deleteById(id);
        } catch (Exception e) {
            throw PaymentDeleteException.deletePaymentException(id);
        }
    }

    public PaymentDTO update(PaymentDTO paymentDTO) {
        return paymentCreationService.update(paymentDTO);
    }

}