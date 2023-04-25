package org.jvc.hotelsearch.controller;

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
    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    public List<Hotel> search(
            @RequestParam String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout_date,
            @RequestParam List<Integer> price_range) {
        return hotelService.search(location, checkin_date, checkout_date, price_range);
    }
}
