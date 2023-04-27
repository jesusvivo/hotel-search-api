package org.jvc.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private int id;
    private String name;
    private String description;
    private Location location;
    @JsonProperty("total_price")
    private int totalPrice;
    private String image;
    private List<Review> reviews;
    @JsonProperty("review_average")
    private double reviewAverage;

    public Hotel() {

    }

    public Hotel(int id, String name, String description, Location location, int totalPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.totalPrice = totalPrice;
        this.image = "";
        this.reviews = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                totalPrice == hotel.totalPrice &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(description, hotel.description) &&
                Objects.equals(location, hotel.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, totalPrice);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(double reviewAverage) {
        this.reviewAverage = reviewAverage;
    }
}
