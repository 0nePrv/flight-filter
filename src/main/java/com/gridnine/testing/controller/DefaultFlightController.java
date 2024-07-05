package com.gridnine.testing.controller;

import static com.gridnine.testing.controller.MenuConstants.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.filter.FilteringService;
import com.gridnine.testing.service.io.IOService;
import com.gridnine.testing.service.rule.FlightFilterRuleService;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;

public final class DefaultFlightController implements FlightController {

  private final IOService ioService;

  private final FilteringService filteringService;

  private final FlightFilterRuleService ruleService;

  private final AtomicBoolean running = new AtomicBoolean(true);

  public DefaultFlightController(IOService ioService, FilteringService filteringService,
      FlightFilterRuleService ruleService) {
    this.ioService = ioService;
    this.filteringService = filteringService;
    this.ruleService = ruleService;
  }

  @Override
  public void start() {
    printUsage();
    while (running.get()) {
      try {
        String input = ioService.readStringWithPrompt(PROMPT);
        processInput(input.strip());
      } catch (NumberFormatException e) {
        ioService.outputStringLine(NAN_ERROR_MSG);
      }
    }
  }

  private void processInput(String input) {
    switch (input) {
      case LINE -> { }
      case HELP -> printUsage();
      case EXIT -> running.set(false);
      case ALL -> printFlightsForRules(Collections.emptyList());
      default -> processFilterRules(input);
    }
  }

  private void processFilterRules(String input) {
    var allRules = ruleService.getAll();
    Set<FlightFilterRule> activeRules = new HashSet<>();
    String[] numbersSeparated = input.split(" ");
    for (var numberString : numbersSeparated) {
      int index = Integer.parseInt(numberString) - 1;
      if (index < 0 || index >= allRules.size()) {
        ioService.outputStringLine(OUT_OF_RANGE_ERROR_MSG + (index + 1));
        return;
      }
      activeRules.add(allRules.get(index));
    }
    printFlightsForRules(activeRules);
  }

  private void printUsage() {
    var rules = ruleService.getAll();
    ioService.outputStringLine(USAGE);
    for (int i = 0; i < rules.size(); i++) {
      ioService.outputStringLine(
          RULE_PRINT_FORMAT.formatted(i + 1, rules.get(i).getName()));
    }
  }

  private void printFlightsForRules(Collection<FlightFilterRule> activeRules) {
    var flights = filteringService.filterFights(activeRules);
    if (flights.isEmpty()) {
      ioService.outputStringLine(EMPTY_FLIGHTS_MSG);
    } else {
      flights.stream().map(Flight::toString).forEach(ioService::outputStringLine);
    }
  }
}
