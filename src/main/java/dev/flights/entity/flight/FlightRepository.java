package dev.flights.entity.flight;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    // @Query("" +
    // "SELECT CASE WHEN COUNT(s) > 0 THEN " +
    // "TRUE ELSE FALSE END " +
    // "FROM Student s " +
    // "WHERE s.email = ?1")
    // Boolean selectExistsEmail(String email);
}
