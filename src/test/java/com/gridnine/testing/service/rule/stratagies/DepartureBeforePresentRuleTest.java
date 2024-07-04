package com.gridnine.testing.service.rule.stratagies;

import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

class DepartureBeforePresentRuleTest {

  private static final Flight MATCHING_FLIGHT = new Flight(
      of(new Segment(now().minusHours(1), now().plusHours(5))));

  private static final Flight NON_MATCHING_FLIGHT = new Flight(
      of(new Segment(now().plusHours(1), now().plusHours(7))));

  private final FlightFilterRule rule = new DepartureBeforePresentRule();

  @Test
  void shouldReturnTrueOnMatchingFlight() {
    assertTrue(rule.test(MATCHING_FLIGHT));
  }

  @Test
  void shouldReturnFalseOnNonMatchingFlight() {
    assertFalse(rule.test(NON_MATCHING_FLIGHT));
  }
}
