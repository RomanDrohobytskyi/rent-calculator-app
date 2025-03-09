package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.payment.repository.PaymentEntityRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentEntityRepository paymentEntityRepository;
    private final ModelMapper modelMapper;

    public List<PaymentDTO> findAll() {
        return paymentEntityRepository.findAll().stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .sorted(comparing(PaymentDTO::getPaymentDate))
                .collect(toList());
    }

    public Optional<PaymentDTO> findById(Long id) {
        return paymentEntityRepository.findById(id)
                .map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    public PaymentDTO findPreviousOrNull(PaymentDTO paymentDTO) {
        return findPrevious(paymentDTO).orElse(null);
    }

    public Optional<PaymentDTO> findPrevious(PaymentDTO paymentDTO) {
        Pair<LocalDate, LocalDate> fromTo = getDateFromToForPreviousPayment(paymentDTO.getPaymentDate());
        return paymentEntityRepository.findByPaymentDateBetween(fromTo.getFirst(), fromTo.getSecond())
                .map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    private Pair<LocalDate, LocalDate> getDateFromToForPreviousPayment(LocalDate paymentDate) {
        if (paymentDate.getMonth().getValue() == 1) {
            LocalDate from = LocalDate.of(paymentDate.minusYears(1).getYear(), paymentDate.plusMonths(11).getMonth(), 1);
            LocalDate to = LocalDate.of(paymentDate.minusYears(1).getYear(), paymentDate.plusMonths(11).getMonth(), paymentDate.minusMonths(1).lengthOfMonth());

            return Pair.of(from, to);
        }

        LocalDate from = LocalDate.of(paymentDate.getYear(), paymentDate.minusMonths(1).getMonth(), 1);
        LocalDate to = LocalDate.of(paymentDate.getYear(), paymentDate.minusMonths(1).getMonth(), paymentDate.minusMonths(1).lengthOfMonth());

        return Pair.of(from, to);
    }

}
