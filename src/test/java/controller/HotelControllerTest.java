package controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jvc.hotelsearch.controller.HotelController;
import org.jvc.hotelsearch.model.Hotel;
import org.jvc.hotelsearch.service.HotelService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    public void search() throws Exception {
        Hotel hotel1 = new Hotel();
        hotel1.setId(1);
        hotel1.setName("Hotel 1");

        Hotel hotel2 = new Hotel();
        hotel2.setId(2);
        hotel2.setName("Hotel 2");

        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        when(hotelService.search(any(), any(), any(), any())).thenReturn(hotels);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
        mockMvc.perform(get("/search")
                        .param("location", "Madrid")
                        .param("checkin_date", "2023-05-01")
                        .param("checkout_date", "2023-05-10")
                        .param("price_range", "50")
                        .param("price_range", "1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Hotel 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Hotel 2"));
    }
}
