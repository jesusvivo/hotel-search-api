package org.jvc.hotelsearch.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.jvc.hotelsearch.model.Hotel;
import org.jvc.hotelsearch.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search")
    public List<Hotel> search(
            @RequestParam @NotEmpty String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam @Size(min = 2, max = 2) List<Integer> priceRange) {
        return hotelService.search(location, checkInDate, checkOutDate, priceRange);
    }
}
