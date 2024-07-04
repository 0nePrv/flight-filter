package com.gridnine.testing.service.factory;

import com.gridnine.testing.model.Flight;
import java.time.LocalDateTime;

public interface FlightFactory {

  Flight createFlight(LocalDateTime... dates);
}
