package com.gridnine.testing;

import java.util.List;

import com.gridnine.testing.controller.FlightController;
import com.gridnine.testing.exception.FlightDetailsException;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.factory.FlightFactory;
import com.gridnine.testing.service.flight.FlightService;
import com.gridnine.testing.service.io.OutputService;

public final class AppRunner {

  public void run(FlightFactory factory, FlightController controller, OutputService outputService,
      FlightService flightService) {
    try {
      List<Flight> flights = FlightBuilder.createTestFlights(factory);
      flightService.addAll(flights);
      controller.start();
    } catch (FlightDetailsException e) {
      outputService.outputStringLine("FLIGHT DETAILS ERROR: " + e.getMessage());
    } catch (Exception e) {
      outputService.outputStringLine("INTERNAL ERROR: " + e.getMessage());
    }
  }
}
