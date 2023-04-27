package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvc.hotelsearch.HotelSearchApplication;
import org.jvc.hotelsearch.controller.HotelController;
import org.jvc.hotelsearch.model.Hotel;
import org.jvc.hotelsearch.model.Location;
import org.jvc.hotelsearch.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelSearchApplication.class)
@AutoConfigureMockMvc
public class HotelControllerTest {

    @MockBean
    private HotelService hotelService;

    @Autowired
    private HotelController hotelController;

    @Autowired
    private MockMvc mockMvc;

    private List<Hotel> hotels;

    @BeforeEach
    void setup() {
        hotelService = mock(HotelService.class);
        hotelController = new HotelController(hotelService);
        mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
        hotels = new ArrayList<>();
        hotels.add(new Hotel(1, "Hotel 1", "Classic hotel", new Location(1, "Madrid"), 80));
        hotels.add(new Hotel(2, "Hotel 2", "Modern hotel", new Location(1, "Madrid"), 90));

    }

    @Test
    public void searchHotels() throws Exception {
        when(hotelService.search(any(), any(), any(), any())).thenReturn(hotels);

        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("checkInDate", "2023-05-01")
                        .param("checkOutDate", "2023-05-10")
                        .param("priceRange", "50")
                        .param("priceRange", "1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hotel 1"))
                .andExpect(jsonPath("$[1].name").value("Hotel 2"));
    }

    @Test
    void searchHotelsWithEmptyLocation() throws Exception {
        mockMvc.perform(get("/search")
                        .param("checkInDate", "2023-05-01")
                        .param("checkOutDate", "2023-05-10")
                        .param("priceRange", "50")
                        .param("priceRange", "1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchHotelsWithMissingDates() throws Exception {
        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("priceRange", "50")
                        .param("priceRange", "1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchHotelsWithInvalidDates() throws Exception {
        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("checkInDate", "invalid-date")
                        .param("checkOutDate", "2023-05-10")
                        .param("priceRange", "50")
                        .param("priceRange", "1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchHotelsWithMissingPriceRange() throws Exception {
        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("checkInDate", "2023-05-01")
                        .param("checkOutDate", "2023-05-10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchHotelsWithInvalidPriceRange() throws Exception {
        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("checkInDate", "2023-05-01")
                        .param("checkOutDate", "2023-05-10")
                        .param("priceRange", "invalid-price")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
