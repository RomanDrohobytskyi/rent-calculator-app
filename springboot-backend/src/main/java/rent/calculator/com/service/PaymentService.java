package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.entity.Payment;
import rent.calculator.com.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    //private final PaymentCreationService paymentCreationService;
    private final ModelMapper modelMapper;

    /*FIXME: Refactor*/

    public PaymentDTO save(PaymentDTO paymentDTO) {
        return null;//paymentCreationService.save(paymentDTO);
    }

    public PaymentDTO recalculate(PaymentDTO paymentDTO) {
        return null;//paymentCreationService.recalculate(paymentDTO);
    }

    public void delete(Long id) {
        //paymentCreationService.delete(id);
    }

    public PaymentDTO update(PaymentDTO paymentDTO) {
        return findById(paymentDTO.getId())
                .map(payment -> update(paymentDTO, payment))
                .orElseThrow(() -> new IllegalArgumentException("Could not update payment because payment with ID: "
                        + paymentDTO.getId() + " does not exist."));
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    private PaymentDTO update(PaymentDTO paymentDTO, Payment payment) {
            modelMapper.map(paymentDTO, payment);
            payment.setModificationDate(now());
            payment = paymentRepository.save(payment);
            return modelMapper.map(payment, PaymentDTO.class);
    }

    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll().stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .sorted(comparing(PaymentDTO::getPaymentDate))
                .collect(toList());
    }

    public Optional<PaymentDTO> findByIdAndMapToDTO(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> modelMapper.map(payment, PaymentDTO.class));
                //.orElseThrow(() -> new IllegalArgumentException("No payment found with id: " + id));
    }

    public Optional<PaymentDTO> findPrevious(PaymentDTO paymentDTO) {
        Pair<LocalDate, LocalDate> fromTo = getDateFromToForPreviousPayment(paymentDTO.getPaymentDate());
        return paymentRepository.findByPaymentDateAfterAndPaymentDateBefore(fromTo.getFirst(), fromTo.getSecond())
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

    public boolean isExist(Long id) {
        return paymentRepository.existsById(id);
    }

}
