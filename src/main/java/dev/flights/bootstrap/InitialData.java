package dev.flights.bootstrap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import dev.flights.entity.airline.Airline;
import dev.flights.entity.airline.AirlineRepository;
import dev.flights.entity.airplane.Airplane;
import dev.flights.entity.airplane.AirplaneRepository;
import dev.flights.entity.flight.Flight;
import dev.flights.entity.flight.FlightRepository;
import dev.flights.entity.airport.Airport;
import dev.flights.entity.airport.AirportRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitialData implements ApplicationRunner {

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private AirplaneRepository airplaneRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Profile("dev")
	@Bean
	public void run(ApplicationArguments args) {
		List<Airport> airports = addAirports();
		List<Airline> airlines = addAirlines();
		List<Airplane> airplanes = addAirplanes(airlines);
		airlines = airlineRepository.findAll();
		List<Flight> flights = addFlights(airports, airlines, airplanes);

		log.info("Initial data has been injected into the database");
	}

	public List<Airport> addAirports() {
		List<Airport> entities = new ArrayList<>();

		entities.add(Airport.builder()
				.name("Debrecen Reptér")
				.iataCode("HU-DEB")
				.country("Hungary")
				.city("Debrecen")
				.build());

		entities.add(Airport.builder()
				.name("Liszt Ferenc Reptér")
				.iataCode("HU-BUD")
				.country("Hungary")
				.city("Budapest")
				.build());

		return airportRepository.saveAllAndFlush(entities);
	}

	public List<Airline> addAirlines() {
		List<Airline> entities = new ArrayList<>();

		entities.add(Airline.builder()
				.name("Wizz Air")
				.logoUrl("https://gccoupons.com/wp-content/uploads/2020/08/Wizz-Air-150x150.png")
				.build());

		entities.add(Airline.builder()
				.name("Wizz Air 2")
				.logoUrl("https://gccoupons.com/wp-content/uploads/2020/08/Wizz-Air-150x150.png")
				.build());

		return airlineRepository.saveAllAndFlush(entities);
	}

	public List<Airplane> addAirplanes(List<Airline> airlines) {
		List<Airplane> entities = new ArrayList<>();

		entities.add(Airplane.builder()
				.type("RX-500")
				.seats(100)
				.airline(airlines.get(0))
				.build());

		entities.add(Airplane.builder()
				.type("HRC-12")
				.seats(1000)
				.hasWifi(true)
				.airline(airlines.get(0))
				.build());

		entities.add(Airplane.builder()
				.type("KEU-345")
				.seats(200)
				.hasWifi(true)
				.airline(airlines.get(1))
				.build());

		entities.add(Airplane.builder()
				.type("TRX-510")
				.seats(150)
				.airline(airlines.get(1))
				.build());

		return airplaneRepository.saveAllAndFlush(entities);
	}

	public List<Flight> addFlights(List<Airport> airports, List<Airline> airlines, List<Airplane> airplanes) {
		List<Flight> entities = new ArrayList<>();

		entities.add(Flight.builder()
				.arrivalAt(LocalDateTime.of(2022, 01, 01, 0, 0, 0))
				.departureAt(LocalDateTime.of(2022, 01, 01, 0, 0, 0))
				.airline(airlines.get(0))
				.airplane(airlines.get(0).getAirplanes().get(0))
				.departureAirport(airports.get(0))
				.arrivalAirport(airports.get(1))
				.build());

		entities.add(Flight.builder()
				.arrivalAt(LocalDateTime.of(2022, 01, 02, 0, 0, 0))
				.departureAt(LocalDateTime.of(2022, 01, 02, 0, 0, 0))
				.airline(airlines.get(0))
				.airplane(airlines.get(0).getAirplanes().get(0))
				.departureAirport(airports.get(1))
				.arrivalAirport(airports.get(0))
				.build());

		return flightRepository.saveAllAndFlush(entities);
	}
}