package com.gridnine.testing.service.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;

public final class FlightFilterRuleServiceImpl implements FlightFilterRuleService {

  private final List<FlightFilterRule> rules = new ArrayList<>();

  @Override
  public synchronized void addAll(Collection<? extends FlightFilterRule> rules) {
    this.rules.addAll(rules);
  }

  @Override
  public synchronized List<FlightFilterRule> getAll() {
    return List.copyOf(rules);
  }
}
