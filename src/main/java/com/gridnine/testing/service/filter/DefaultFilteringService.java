package com.gridnine.testing.service.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.flight.FlightService;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;

public final class DefaultFilteringService implements FilteringService {

  private final FlightService flightService;

  public DefaultFilteringService(FlightService flightService) {
    this.flightService = flightService;
  }

  @Override
  public List<Flight> filterFights(Collection<FlightFilterRule> rules) {
    List<Flight> flights = new ArrayList<>(flightService.getAll());
    for (var rule : rules) {
      flights.removeIf(rule);
    }
    return flights;
  }
}
