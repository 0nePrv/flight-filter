package com.gridnine.testing.service.holder;

import com.gridnine.testing.model.Flight;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class FlightsHolder {

  private static final List<Flight> flights = new ArrayList<>();

  private FlightsHolder() {
  }

  public static synchronized void addFlights(Collection<? extends Flight> newFlights) {
    flights.addAll(newFlights);
  }

  public static synchronized List<Flight> getAllFlights() {
    return new ArrayList<>(flights);
  }
}
