package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.model.entity.RentPrice;
import rent.calculator.com.repository.RentPriceRepository;

@Service
@RequiredArgsConstructor
public class RentPriceService {
    private final RentPriceRepository rentPriceRepository;
    private final ModelMapper modelMapper;

    public RentPriceDTO getActual() {
        RentPrice rentPrice = rentPriceRepository.getByActual(true);
        return modelMapper.map(rentPrice, RentPriceDTO.class);
    }
}
