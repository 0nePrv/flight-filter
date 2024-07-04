package com.gridnine.testing.service.rule.stratagies;

import com.gridnine.testing.model.Flight;

public final class ArriveBeforeDepartSegmentsRule implements FlightFilterRule {

  @Override
  public boolean test(Flight flight) {
    for (var segment : flight.getSegments()) {
      if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getName() {
    return "Flight contains segment with date of arrival before date of departure";
  }
}
