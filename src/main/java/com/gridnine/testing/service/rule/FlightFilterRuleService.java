package com.gridnine.testing.service.rule;

import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;
import java.util.Collection;
import java.util.List;

public interface FlightFilterRuleService {

  void addAll(Collection<? extends FlightFilterRule> rule);

  List<FlightFilterRule> getAll();
}
