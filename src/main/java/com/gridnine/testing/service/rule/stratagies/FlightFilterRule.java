package com.gridnine.testing.service.rule.stratagies;

import com.gridnine.testing.model.Flight;
import java.util.function.Predicate;


public interface FlightFilterRule extends Predicate<Flight> {

  @Override
  boolean test(Flight flight);

  String getName();
}
