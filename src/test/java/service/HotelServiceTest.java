package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvc.hotelsearch.model.Hotel;
import org.jvc.hotelsearch.model.Location;
import org.jvc.hotelsearch.service.HotelService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelServiceTest {

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelService();
    }

    @Test
    void search() {
        LocalDate checkinDate = LocalDate.of(2023, 5, 1);
        LocalDate checkoutDate = LocalDate.of(2023, 5, 10);
        List<Integer> priceRange = Arrays.asList(50, 100);

        List<Hotel> expectedHotels = Arrays.asList(new Hotel(1, "Hotel 1", "Classic hotel", new Location(1, "Madrid"), 80), new Hotel(2, "Hotel 2", "Modern hotel", new Location(1, "Madrid"), 90));

        HotelService hotelService = new HotelService();
        hotelService.addHotel(new Hotel(1, "Hotel 1", "Classic hotel", new Location(1, "Madrid"), 80));
        hotelService.addHotel(new Hotel(2, "Hotel 2", "Modern hotel", new Location(1, "Madrid"), 90));
        hotelService.addHotel(new Hotel(5, "Hotel C", "Small hotel", new Location(2, "London"), 100));

        List<Hotel> actualHotels = hotelService.search("Madrid", checkinDate, checkoutDate, priceRange);

        assertEquals(expectedHotels.size(), actualHotels.size());
        assertEquals(expectedHotels.get(0).getName(), actualHotels.get(0).getName());
        assertEquals(expectedHotels.get(1).getName(), actualHotels.get(1).getName());
    }

    @Test
    void searchWithNoResults() {
        LocalDate checkinDate = LocalDate.of(2023, 5, 1);
        LocalDate checkoutDate = LocalDate.of(2023, 5, 10);
        List<Integer> priceRange = Arrays.asList(150, 200);

        HotelService hotelService = new HotelService();
        hotelService.addHotel(new Hotel(1, "Hotel 1", "Classic hotel", new Location(1, "Madrid"), 80));
        hotelService.addHotel(new Hotel(2, "Hotel 2", "Modern hotel", new Location(1, "Madrid"), 90));
        hotelService.addHotel(new Hotel(5, "Hotel C", "Small hotel", new Location(2, "London"), 100));

        List<Hotel> actualHotels = hotelService.search("Madrid", checkinDate, checkoutDate, priceRange);

        assertEquals(0, actualHotels.size());
    }

    @Test
    void searchWithInvalidDates() {
        LocalDate checkinDate = LocalDate.of(2023, 5, 10);
        LocalDate checkoutDate = LocalDate.of(2023, 5, 1);
        List<Integer> priceRange = Arrays.asList(50, 100);

        HotelService hotelService = new HotelService();
        hotelService.addHotel(new Hotel(1, "Hotel 1", "Classic hotel", new Location(1, "Madrid"), 80));
        hotelService.addHotel(new Hotel(2, "Hotel 2", "Modern hotel", new Location(1, "Madrid"), 90));
        hotelService.addHotel(new Hotel(5, "Hotel C", "Small hotel", new Location(2, "London"), 100));

        List<Hotel> actualHotels = hotelService.search("Madrid", checkinDate, checkoutDate, priceRange);

        assertEquals(0, actualHotels.size());
    }


}
