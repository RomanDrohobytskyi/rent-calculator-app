package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.entity.Payment;
import rent.calculator.com.repository.PaymentRepository;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class PaymentCreationService {
    private final PaymentRepository paymentRepository;
    private final PaymentMessageCreationService paymentMessageCreationService;
    private final ModelMapper modelMapper;

    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setCreationDate(now());
        payment.setEmailMessage(paymentMessageCreationService.createMessage(paymentDTO));
        payment = paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
/*        return paymentRepository.findById(id)
                .map(payment -> paymentRepository.delete(payment))
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .orElseThrow(IllegalArgumentException::new);*/
    }

    public PaymentDTO delete(Payment payment) {
        //paymentRepository.delete(payment);
        return null;
    }
}
