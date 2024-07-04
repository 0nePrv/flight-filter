package com.gridnine.testing.service.rule.stratagies;

import com.gridnine.testing.model.Flight;
import java.time.LocalDateTime;

public final class DepartureBeforePresentRule implements FlightFilterRule {

  @Override
  public boolean test(Flight flight) {
    var firstSegment = flight.getSegments().get(0);
    return firstSegment.getDepartureDate().isBefore(LocalDateTime.now());
  }

  @Override
  public String getName() {
    return "Flight departure is before present";
  }
}
