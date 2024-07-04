package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.factory.FlightFactory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class FlightBuilder {

  private FlightBuilder() {
  }

  public static List<Flight> createTestFlights(FlightFactory factory) {
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
    return Arrays.asList(

        //A normal flight with two hour duration
        factory.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

        //A normal multi segment flight
        factory.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
            threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

        //A flight departing in the past
        factory.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),

        //A flight that departs before it arrives
        factory.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

        //A flight with more than two hours ground time
        factory.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
            threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

        //Another flight with more than two hours ground time
        factory.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
            threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
            threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
  }
}
