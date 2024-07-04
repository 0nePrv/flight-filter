package com.gridnine.testing.service.rule;

import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class FlightFilterRuleServiceImpl implements FlightFilterRuleService {

  private final List<FlightFilterRule> rules = new CopyOnWriteArrayList<>();

  @Override
  public void addAll(Collection<? extends FlightFilterRule> rules) {
    this.rules.addAll(rules);
  }

  @Override
  public List<FlightFilterRule> getAll() {
    return rules;
  }

  @Override
  public FlightFilterRule getByIndex(int index) {
    return rules.get(index);
  }
}
