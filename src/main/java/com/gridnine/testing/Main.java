package com.gridnine.testing;

import com.gridnine.testing.controller.DefaultFlightController;
import com.gridnine.testing.controller.FlightController;
import com.gridnine.testing.service.factory.FlightFactory;
import com.gridnine.testing.service.factory.FlightFactoryImpl;
import com.gridnine.testing.service.filter.DefaultFilteringService;
import com.gridnine.testing.service.filter.FilteringService;
import com.gridnine.testing.service.flight.FlightService;
import com.gridnine.testing.service.flight.FlightServiceImpl;
import com.gridnine.testing.service.io.IOService;
import com.gridnine.testing.service.io.IOServiceStreams;
import com.gridnine.testing.service.rule.FlightFilterRuleService;
import com.gridnine.testing.service.rule.FlightFilterRuleServiceImpl;
import com.gridnine.testing.service.rule.stratagies.ArriveBeforeDepartSegmentsRule;
import com.gridnine.testing.service.rule.stratagies.DepartureBeforePresentRule;
import com.gridnine.testing.service.rule.stratagies.TotalRestTimeExceedsTwoHoursRule;
import java.util.List;

public final class Main {

  public static void main(String[] args) {
    IOService ioService = new IOServiceStreams(System.out, System.in);
    FlightFactory factory = new FlightFactoryImpl();
    FlightService flightService = new FlightServiceImpl();
    FilteringService filteringService = new DefaultFilteringService(flightService);
    FlightFilterRuleService ruleService = new FlightFilterRuleServiceImpl();
    ruleService.addAll(List.of(
        new DepartureBeforePresentRule(),
        new ArriveBeforeDepartSegmentsRule(),
        new TotalRestTimeExceedsTwoHoursRule()));
    FlightController controller = new DefaultFlightController(ioService, filteringService, ruleService);
    new AppRunner().run(factory, controller, ioService, flightService);
  }
}