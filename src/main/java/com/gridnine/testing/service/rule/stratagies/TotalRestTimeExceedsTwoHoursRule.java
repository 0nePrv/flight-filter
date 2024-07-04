package com.gridnine.testing.service.rule.stratagies;

import static java.time.Duration.between;
import static java.time.Duration.ofHours;

import com.gridnine.testing.model.Flight;
import java.time.Duration;

public final class TotalRestTimeExceedsTwoHoursRule implements FlightFilterRule {

  @Override
  public boolean test(Flight flight) {
    var totalRestTime = Duration.ZERO;
    var segments = flight.getSegments();
    var lastArrivalDateTime = segments.get(0).getArrivalDate();
    for (int i = 1; i < segments.size(); i++) {
      var segment = segments.get(i);
      var currentDepartureDateTime = segment.getDepartureDate();
      totalRestTime = totalRestTime.plus(between(lastArrivalDateTime ,currentDepartureDateTime));
      lastArrivalDateTime = segment.getArrivalDate();
      if (totalRestTime.compareTo(ofHours(2)) > 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getName() {
    return "Flight total rest time exceeds two hours";
  }
}
