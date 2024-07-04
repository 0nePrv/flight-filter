package com.gridnine.testing.service.factory;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gridnine.testing.exception.EmptySegmentsFlightException;
import com.gridnine.testing.exception.NullArrivalDateTimeException;
import com.gridnine.testing.exception.NullDepartureDateTimeException;
import com.gridnine.testing.exception.OddNumberOfSegmentsException;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class FlightFactoryImplTest {

  private final FlightFactory factory = new FlightFactoryImpl();

  @Test
  void shouldThrowExceptionWhenNoDatesPresent() {
    assertThrows(EmptySegmentsFlightException.class, factory::createFlight
        , "Flight does not contain any segments");
  }

  @Test
  void shouldThrowExceptionOnOddNumberOfDates() {
    assertThrows(OddNumberOfSegmentsException.class, () -> factory.createFlight(now())
        , "Even number of dates must be passed");
  }

  @Test
  void shouldThrowExceptionOnNullDepartureDate() {
    assertThrows(NullDepartureDateTimeException.class, () -> factory.createFlight(null, now())
        , "Departure date can not be null");
  }

  @Test
  void shouldThrowExceptionOnNullArrivalDate() {
    assertThrows(NullArrivalDateTimeException.class, () -> factory.createFlight(now(), null)
        , "Arrival date can not be null");
  }

  @Test
  void shouldCreateFlightOnCorrectDates() {
    LocalDateTime departureDateTime = now().minusHours(1);
    LocalDateTime arrivalDateTime = now();
    LocalDateTime[] dates = {departureDateTime, arrivalDateTime};

    Flight flight = assertDoesNotThrow(() -> factory.createFlight(dates));
    List<Segment> segments = flight.getSegments();

    assertEquals(departureDateTime, segments.get(0).getDepartureDate());
    assertEquals(arrivalDateTime, segments.get(0).getArrivalDate());
  }
}