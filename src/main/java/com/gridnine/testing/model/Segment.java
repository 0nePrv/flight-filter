package com.gridnine.testing.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Segment {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd'T'HH:mm");

  private final LocalDateTime departureDate;

  private final LocalDateTime arrivalDate;

  public Segment(final LocalDateTime dep, final LocalDateTime arr) {
    departureDate = dep;
    arrivalDate = arr;
  }

  public LocalDateTime getDepartureDate() {
    return departureDate;
  }

  public LocalDateTime getArrivalDate() {
    return arrivalDate;
  }

  @Override
  public String toString() {
    return "Segment [" + departureDate.format(FORMATTER) + " | " + arrivalDate.format(
        FORMATTER) + ']';
  }
}