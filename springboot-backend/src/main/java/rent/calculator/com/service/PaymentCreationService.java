package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.entity.Payment;
import rent.calculator.com.repository.PaymentRepository;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCreationService {
    private final PaymentRepository paymentRepository;
    private final PaymentMessageCreationService paymentMessageCreationService;
    private final PaymentCalculationService paymentCalculationService;
    private final ModelMapper modelMapper;

    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = adaptPayment(paymentDTO);
        payment = paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    private Payment adaptPayment(PaymentDTO paymentDTO) {
        addRentCalculations(paymentDTO);
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setCreationDate(now());
        payment.setEmailMessage(paymentMessageCreationService.createMessage(paymentDTO));
        return payment;
    }

    private void addRentCalculations(PaymentDTO paymentDTO) {
        paymentCalculationService.setPaymentCalculations(paymentDTO);
    }

    public PaymentDTO recalculate(PaymentDTO paymentDTO) {
        return save(paymentDTO);
    }

    public void delete(Long id) {
        try{
            paymentRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Could not delete payment " + id + ". " + e);
        }
    }
}
