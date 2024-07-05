package com.gridnine.testing.service.flight;

import java.util.Collection;
import java.util.List;

import com.gridnine.testing.model.Flight;

public interface FlightService {

  void addAll(Collection<? extends Flight> rule);

  List<Flight> getAll();
}
