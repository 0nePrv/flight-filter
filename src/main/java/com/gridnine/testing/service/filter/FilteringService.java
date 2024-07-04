package com.gridnine.testing.service.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;
import java.util.Collection;
import java.util.List;

public interface FilteringService {

  List<Flight> filterFights(Collection<FlightFilterRule> rules);
}
