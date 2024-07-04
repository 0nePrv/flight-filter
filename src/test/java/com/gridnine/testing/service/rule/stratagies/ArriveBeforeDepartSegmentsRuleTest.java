package com.gridnine.testing.service.rule.stratagies;

import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

class ArriveBeforeDepartSegmentsRuleTest {

  private static final Flight MATCHING_FLIGHT = new Flight(
      of(new Segment(now(), now().minusHours(1))));

  private static final Flight NON_MATCHING_FLIGHT = new Flight(
      of(new Segment(now().minusHours(1), now())));

  private final FlightFilterRule rule = new ArriveBeforeDepartSegmentsRule();

  @Test
  void shouldReturnTrueOnMatchingFlight() {
    assertTrue(rule.test(MATCHING_FLIGHT));
  }

  @Test
  void shouldReturnFalseOnNonMatchingFlight() {
    assertFalse(rule.test(NON_MATCHING_FLIGHT));
  }
}
