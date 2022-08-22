package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.entity.PaymentMessage;
import rent.calculator.com.repository.PaymentMessageRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.math.BigDecimal.valueOf;

@Service
@RequiredArgsConstructor
public class PaymentMessageService {
    private final PaymentMessageRepository paymentMessageRepository;
    private final ModelMapper modelMapper;

    public PaymentMessageDTO getActual() {
        PaymentMessage paymentMessage = paymentMessageRepository.getByActual(true);
        return modelMapper.map(paymentMessage, PaymentMessageDTO.class);
    }
}
