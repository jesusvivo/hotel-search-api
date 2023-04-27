# Hotel Search

A simple Spring Boot application for searching hotels based on location, check-in and check-out dates, and price range.

## Features

- Search hotels by location, check-in and check-out dates, and price range
- Validate search inputs
- Return hotels sorted by average review rating

## Technologies Used

- Java
- Spring Boot

## Getting Started

1. Clone this repository
2. Import the project into your favorite IDE
3. Ensure that you have the required dependencies installed (see `pom.xml`)
4. Run the `HotelSearchApplication` class to start the Spring Boot application

## Usage

Once the application is running, you can perform a search by sending a GET request to the `/search` endpoint with the following query parameters:

- `location`: the location of the hotels (e.g., "Madrid")
- `checkInDate`: the check-in date (e.g., "2023-05-01")
- `checkOutDate`: the check-out date (e.g., "2023-05-10")
- `priceRange`: a list containing the minimum and maximum prices (e.g., [50, 1000])

Example request:

`GET /search?location=Madrid&checkInDate=2023-05-01&checkOutDate=2023-05-10&priceRange=50&priceRange=1000`
