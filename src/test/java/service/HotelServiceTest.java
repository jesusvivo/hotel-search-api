package service;

import org.jvc.hotelsearch.model.Hotel;
import org.junit.jupiter.api.Test;
import org.jvc.hotelsearch.service.HotelService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HotelServiceTest {

    @Test
    public void search() {
        HotelService hotelService = new HotelService();

        String location = "Madrid";
        LocalDate checkinDate = LocalDate.parse("2023-05-01");
        LocalDate checkoutDate = LocalDate.parse("2023-05-10");
        List<Integer> priceRange = List.of(50, 1000);

        List<Hotel> result = hotelService.search(location, checkinDate, checkoutDate, priceRange);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

        for (Hotel hotel : result) {
            assertThat(hotel.getLocation().getName()).isEqualTo(location);
            assertThat(hotel.getTotalPrice()).isBetween(priceRange.get(0), priceRange.get(1));
        }
    }
}
