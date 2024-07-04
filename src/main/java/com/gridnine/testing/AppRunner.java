package com.gridnine.testing;

import com.gridnine.testing.controller.FlightController;
import com.gridnine.testing.exception.FlightDetailsException;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.factory.FlightFactory;
import com.gridnine.testing.service.holder.FlightsHolder;
import com.gridnine.testing.service.io.OutputService;
import java.util.List;

public final class AppRunner {

  public void run(FlightFactory factory, FlightController controller, OutputService outputService) {
    try {
      List<Flight> flights = FlightBuilder.createTestFlights(factory);
      FlightsHolder.addFlights(flights);
      controller.start();
    } catch (FlightDetailsException e) {
      outputService.outputStringLine("FLIGHT DETAILS ERROR: " + e.getMessage());
    } catch (Exception e) {
      outputService.outputStringLine("INTERNAL ERROR: " + e.getMessage());
    }
  }
}
