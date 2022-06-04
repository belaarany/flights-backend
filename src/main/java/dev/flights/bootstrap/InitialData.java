package dev.flights.bootstrap;

import java.time.LocalDateTime;

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
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitialData implements ApplicationRunner {

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private AirplaneRepository airplaneRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Profile("dev")
	@Bean
	public void run(ApplicationArguments args) {
		Airline airline_1 = airlineRepository.save(
				Airline.builder()
						.name("Wizz Air")
						.logoUrl("https://gccoupons.com/wp-content/uploads/2020/08/Wizz-Air-150x150.png")
						.build());

		Airplane airline_1_airplane_1 = airplaneRepository.save(
				Airplane.builder()
						.type("RX-500")
						.seats(100)
						.airline(airline_1)
						.build());

		airplaneRepository.save(
				Airplane.builder()
						.type("WK-73")
						.seats(50)
						.airline(airline_1)
						.build());

		Airline airline_2 = airlineRepository.save(
				Airline.builder()
						.name("Wizz Air 2")
						.logoUrl("https://gccoupons.com/wp-content/uploads/2020/08/Wizz-Air-150x150.png")
						.build());

		airplaneRepository.save(
				Airplane.builder()
						.type("FFKG-500")
						.seats(200)
						.airline(airline_2)
						.build());

		airplaneRepository.save(
				Airplane.builder()
						.type("BB-9")
						.seats(6000)
						.airline(airline_2)
						.build());

		flightRepository.save(
				Flight.builder()
						.arrivalAt(LocalDateTime.of(2022, 01, 01, 0, 0, 0))
						.departureAt(LocalDateTime.of(2022, 01, 01, 0, 0, 0))
						.airline(airline_1)
						.airplane(airline_1_airplane_1)
						.build());

		log.info("Initial data has been injected into the database");
	}
}