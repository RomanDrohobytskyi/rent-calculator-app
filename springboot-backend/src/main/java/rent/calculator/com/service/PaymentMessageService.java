package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.entity.PaymentMessage;
import rent.calculator.com.repository.PaymentMessageRepository;

@Service
@RequiredArgsConstructor
public class PaymentMessageService {
    private final PaymentMessageRepository paymentMessageRepository;
    private final ModelMapper modelMapper;

    public PaymentMessageDTO getActual() {
        PaymentMessage paymentMessage = paymentMessageRepository.getByActual(true);
        return paymentMessage != null
                ? modelMapper.map(paymentMessage, PaymentMessageDTO.class)
                : null;
    }

    public PaymentMessageDTO update(PaymentMessageDTO paymentMessageDTO) {
        PaymentMessage paymentMessage = paymentMessageRepository.getByIdAndActual(paymentMessageDTO.getId(), true);
        PaymentMessage updated = modelMapper.map(paymentMessageDTO, PaymentMessage.class);
        modelMapper.map(paymentMessageDTO, paymentMessage);
        updated.setId(paymentMessage.getId());
        updated = paymentMessageRepository.save(updated);
        return modelMapper.map(updated, PaymentMessageDTO.class);
    }
}
