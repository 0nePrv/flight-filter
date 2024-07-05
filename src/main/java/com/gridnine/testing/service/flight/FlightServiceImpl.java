package com.gridnine.testing.service.flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gridnine.testing.model.Flight;

public final class FlightServiceImpl implements FlightService {

  private final List<Flight> rules = new ArrayList<>();

  @Override
  public synchronized void addAll(Collection<? extends Flight> rules) {
    this.rules.addAll(rules);
  }

  @Override
  public synchronized List<Flight> getAll() {
    return List.copyOf(rules);
  }
}
