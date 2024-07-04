package com.gridnine.testing.service.rule.stratagies;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import java.util.List;
import org.junit.jupiter.api.Test;

class TotalRestTimeExceedsTwoHoursRuleTest {

  private static final Flight NON_MATCHING_FLIGHT = new Flight(List.of(
      new Segment(now(), now().plusHours(2))
  ));

  private static final Flight MATCHING_FLIGHT = new Flight(List.of(
      new Segment(now(), now().plusHours(2)),
      new Segment(now().plusHours(3), now().plusHours(4)),
      new Segment(now().plusHours(6), now().plusHours(7)))
  );

  FlightFilterRule rule = new TotalRestTimeExceedsTwoHoursRule();

  @Test
  void shouldReturnTrueOnMatchingFlight() {
    assertTrue(rule.test(MATCHING_FLIGHT));
  }

  @Test
  void shouldReturnFalseOnNonMatchingFlight() {
    assertFalse(rule.test(NON_MATCHING_FLIGHT));
  }
}