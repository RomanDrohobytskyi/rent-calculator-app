package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.entity.Payment;
import rent.calculator.com.payment.exception.PaymentUpdateException;
import rent.calculator.com.payment.message.PaymentMessageCreationService;
import rent.calculator.com.payment.repository.PaymentEntityRepository;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCreationService {
    private final PaymentEntityRepository paymentEntityRepository;
    private final PaymentMessageCreationService paymentMessageCreationService;
    private final PaymentService paymentService;
    private final PaymentCalculationService paymentCalculationService;
    private final ModelMapper modelMapper;

    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = adaptPayment(paymentDTO);
        payment = paymentEntityRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    private Payment adaptPayment(PaymentDTO paymentDTO) {
        addRentCalculations(paymentDTO);
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setCreationDate(now());
        payment.setEmailMessage(createMessage(paymentDTO));
        return payment;
    }

    private String createMessage(PaymentDTO paymentDTO) {
        PaymentDTO previousPayment = paymentService.findPreviousOrNull(paymentDTO);
        return paymentMessageCreationService.createMessage(paymentDTO, previousPayment);
    }

    private void addRentCalculations(PaymentDTO paymentDTO) {
        paymentCalculationService.setPaymentCalculations(paymentDTO);
    }

    public PaymentDTO recalculate(PaymentDTO paymentDTO) {
        return save(paymentDTO);
    }

    public PaymentDTO update(PaymentDTO paymentDTO) {
        if (paymentEntityRepository.existsById(paymentDTO.getId())) {
            Payment toBeUpdated = modelMapper.map(paymentDTO, Payment.class);
            toBeUpdated.setModificationDate(now());
            toBeUpdated = paymentEntityRepository.save(toBeUpdated);
            return modelMapper.map(toBeUpdated, PaymentDTO.class);
        } else {
            throw new PaymentUpdateException(paymentDTO.getId());
        }
    }
}
