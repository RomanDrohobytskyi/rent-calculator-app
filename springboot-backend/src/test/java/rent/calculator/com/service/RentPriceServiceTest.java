package rent.calculator.com.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import rent.calculator.com.repository.RentPriceRepository;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class RentPriceServiceTest {
    @InjectMocks
    RentPriceService rentPriceService;
    @Mock
    RentPriceRepository rentPriceRepository;
    @Mock
    ModelMapper modelMapper = new ModelMapper();

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void shouldNotThrowErrorWhileDeactivatingNull() {
        //given
        when(rentPriceRepository.getByActualIsTrue()).thenReturn(empty());

        //when
        assertDoesNotThrow(() -> rentPriceService.deactivateActualRentPrice());
    }
}
