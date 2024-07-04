package com.gridnine.testing.service.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.holder.FlightsHolder;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;
import java.util.Collection;
import java.util.List;

public final class DefaultFilteringService implements FilteringService {

  @Override
  public List<Flight> filterFights(Collection<FlightFilterRule> rules) {
    List<Flight> flights = FlightsHolder.getAllFlights();
    for (var rule : rules) {
      flights.removeIf(rule);
    }
    return flights;
  }
}
