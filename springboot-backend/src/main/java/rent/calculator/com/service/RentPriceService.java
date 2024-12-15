package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.model.entity.RentPrice;
import rent.calculator.com.repository.RentPriceRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RentPriceService {
    private final RentPriceRepository rentPriceRepository;
    private final ModelMapper modelMapper;

    public RentPriceDTO addActualRentPrice(RentPriceDTO rentPrice) {
        deactivateActualRentPrice();
        RentPrice rentPriceToBeSaved = modelMapper.map(rentPrice, RentPrice.class);
        rentPriceToBeSaved.setId(null);
        rentPriceToBeSaved.setActual(true);
        return modelMapper.map(rentPriceRepository.save(rentPriceToBeSaved), RentPriceDTO.class);
    }

    public void deactivateActualRentPrice() {
        rentPriceRepository.getByActualIsTrue()
                .map(RentPrice::deactivate)
                .map(rentPriceRepository::save);
    }

    public RentPriceDTO getActual() {
        return rentPriceRepository.getByActualIsTrue()
                .map(price -> modelMapper.map(price, RentPriceDTO.class))
                .orElseGet(() -> RentPriceDTO.builder().build());
    }

    public List<RentPriceDTO> getArchivalPrices() {
        return rentPriceRepository.getAllByActualIsFalse().stream()
                .map(rentPrice -> modelMapper.map(rentPrice, RentPriceDTO.class))
                .collect(Collectors.toList());
    }
}
