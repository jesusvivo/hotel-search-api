package org.jvc.hotelsearch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvc.hotelsearch.model.Hotel;
import org.jvc.hotelsearch.model.Review;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private List<Hotel> hotels;

    public HotelService() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("hotels.json").getInputStream();
            hotels = objectMapper.readValue(inputStream, new TypeReference<List<Hotel>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read hotel data from JSON file", e);
        }
    }

    public List<Hotel> search(String location, LocalDate checkinDate, LocalDate checkoutDate, List<Integer> priceRange) {
        List<Hotel> filteredHotels = hotels.stream()
                .filter(hotel -> hotel.getLocation().getName().equalsIgnoreCase(location))
                .filter(hotel -> hotel.getTotalPrice() >= priceRange.get(0) && hotel.getTotalPrice() <= priceRange.get(1))
                .collect(Collectors.toList());

        // Calculate average review rating for each hotel
        for (Hotel hotel : filteredHotels) {
            double averageRating = hotel.getReviews().stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            hotel.setReviewAverage(averageRating);
        }

        // Sort hotels by review average, highest rated first
        filteredHotels.sort(Comparator.comparingDouble(Hotel::getReviewAverage).reversed());

        return filteredHotels;
    }
}

