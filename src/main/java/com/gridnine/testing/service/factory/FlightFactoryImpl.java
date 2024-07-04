package com.gridnine.testing.service.factory;

import com.gridnine.testing.exception.EmptySegmentsFlightException;
import com.gridnine.testing.exception.NullArrivalDateTimeException;
import com.gridnine.testing.exception.NullDepartureDateTimeException;
import com.gridnine.testing.exception.OddNumberOfSegmentsException;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class FlightFactoryImpl implements FlightFactory {

  @Override
  public Flight createFlight(final LocalDateTime... dates) {
    if (dates.length == 0) {
      throw new EmptySegmentsFlightException("Flight does not contain any segments");
    }
    if (dates.length % 2 != 0) {
      throw new OddNumberOfSegmentsException("Even number of dates must be passed");
    }
    List<Segment> segments = new ArrayList<>(dates.length / 2);
    fillSegments(dates, segments);
    return new Flight(segments);
  }

  private void fillSegments(LocalDateTime[] dates, List<Segment> segments) {
    for (int i = 0; i < dates.length - 1; i += 2) {
      if (dates[i] == null) {
        throw new NullDepartureDateTimeException("Departure date can not be null");
      }
      if (dates[i + 1] == null) {
        throw new NullArrivalDateTimeException("Arrival date can not be null");
      }
      segments.add(new Segment(dates[i], dates[i + 1]));
    }
  }
}
