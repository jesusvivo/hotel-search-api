package org.jvc.hotelsearch.model;

import java.util.List;

public class Hotel {
    private String id;
    private String name;
    private String description;
    private Location location;
    private int totalPrice;
    private String image;
    private List<Review> reviews;
    private double reviewAverage;

    public Hotel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
