package dev.flights.service.itinerary;

import lombok.Data;
import dev.flights.entity.airport.Airport;

@Data
public class ItineraryOptions {
    public Airport departureAirport;
    public Airport arrivalAirport;
}
