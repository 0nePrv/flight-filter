package com.gridnine.testing.service.filter;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.holder.FlightsHolder;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DefaultFilteringServiceTest {

  private static final Flight MATCHING_FLIGHT = new Flight(of(
      new Segment(now().minusHours(7), now().minusHours(1)))
  );

  private static final Flight NON_MATCHING_FLIGHT = new Flight(of(
      new Segment(now().minusDays(3), now().minusDays(2)),
      new Segment(now().minusDays(1), now().plusDays(1)))
  );

  private static final FlightFilterRule RULE_1 = new FlightFilterRule() {
    @Override
    public boolean test(Flight flight) {
      return flight.getSegments().size() == 1;
    }

    @Override
    public String getName() {
      return "flight has one segment";
    }
  };

  private static final FlightFilterRule RULE_2 = new FlightFilterRule() {
    @Override
    public boolean test(Flight flight) {
      return flight.getSegments().get(flight.getSegments().size() - 1)
          .getArrivalDate().isBefore(now());
    }

    @Override
    public String getName() {
      return "flight is arrived";
    }
  };

  private final FilteringService service = new DefaultFilteringService();

  @Test
  void shouldReturnAllFlightsIfNoRulesPresent() {
    try (var flightsHolder = Mockito.mockStatic(FlightsHolder.class)) {
      flightsHolder.when(FlightsHolder::getAllFlights)
          .thenReturn(new ArrayList<>(of(NON_MATCHING_FLIGHT, MATCHING_FLIGHT)));

      List<Flight> flights = service.filterFights(emptyList());

      assertEquals(of(NON_MATCHING_FLIGHT, MATCHING_FLIGHT), flights);
    }
  }

  @Test
  void shouldReturnFlightsMatchingRules() {
    try (var flightsHolder = Mockito.mockStatic(FlightsHolder.class)) {
      flightsHolder.when(FlightsHolder::getAllFlights)
          .thenReturn(new ArrayList<>(of(NON_MATCHING_FLIGHT, MATCHING_FLIGHT)));

      List<Flight> flights = service.filterFights(of(RULE_1, RULE_2));

      assertEquals(of(NON_MATCHING_FLIGHT), flights);
    }
  }
}