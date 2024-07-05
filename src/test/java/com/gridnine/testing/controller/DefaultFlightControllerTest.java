package com.gridnine.testing.controller;

import static com.gridnine.testing.controller.MenuConstants.ALL;
import static com.gridnine.testing.controller.MenuConstants.EMPTY_FLIGHTS_MSG;
import static com.gridnine.testing.controller.MenuConstants.EXIT;
import static com.gridnine.testing.controller.MenuConstants.HELP;
import static com.gridnine.testing.controller.MenuConstants.LINE;
import static com.gridnine.testing.controller.MenuConstants.NAN_ERROR_MSG;
import static com.gridnine.testing.controller.MenuConstants.OUT_OF_RANGE_ERROR_MSG;
import static com.gridnine.testing.controller.MenuConstants.PROMPT;
import static com.gridnine.testing.controller.MenuConstants.RULE_PRINT_FORMAT;
import static com.gridnine.testing.controller.MenuConstants.USAGE;
import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.filter.FilteringService;
import com.gridnine.testing.service.io.IOService;
import com.gridnine.testing.service.rule.FlightFilterRuleService;
import com.gridnine.testing.service.rule.stratagies.FlightFilterRule;

@ExtendWith(MockitoExtension.class)
class DefaultFlightControllerTest {

  private static final FlightFilterRule RULE = new FlightFilterRule() {

    @Override
    public boolean test(Flight flight) {
      return false;
    }

    @Override
    public String getName() {
      return "RULE NAME";
    }
  };

  private static final Flight FLIGHT = new Flight(
      singletonList(new Segment(now().minusDays(1), now())));

  @Mock
  private IOService ioService;

  @Mock
  private FilteringService filteringService;

  @Mock
  private FlightFilterRuleService ruleService;

  @InjectMocks
  private DefaultFlightController controller;

  @Test
  void shouldStopLoopOnExitFlagEntered() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn(EXIT);
    when(ruleService.getAll()).thenReturn(emptyList());

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(USAGE);
  }

  @Test
  void shouldStopLoopOnLineFlagEntered() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn(LINE, EXIT);
    when(ruleService.getAll()).thenReturn(emptyList());

    assertDoesNotThrow(controller::start);

    verify(ioService, times(2)).readStringWithPrompt(PROMPT);
    verify(ioService, times(1)).outputStringLine(USAGE);
  }

  @Test
  void shouldPrintUsageWhenHelpFlagIsEntered() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn(HELP, EXIT);
    when(ruleService.getAll()).thenReturn(singletonList(RULE));

    assertDoesNotThrow(controller::start);

    verify(ioService, times(2)).outputStringLine(USAGE);
    verify(ioService, times(2)).outputStringLine(
        RULE_PRINT_FORMAT.formatted(1, RULE.getName()));
  }

  @Test
  void shouldPrintAllFlightsOnAllFlagWhenFlightsArePresent() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn(ALL, EXIT);
    when(ruleService.getAll()).thenReturn(emptyList());
    when(filteringService.filterFights(emptyList())).thenReturn(singletonList(FLIGHT));

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(FLIGHT.toString());
  }

  @Test
  void shouldPrintMessageOnAllFlagWhenFlightsAreEmpty() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn(ALL, EXIT);
    when(ruleService.getAll()).thenReturn(emptyList());
    when(filteringService.filterFights(emptyList())).thenReturn(emptyList());

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(EMPTY_FLIGHTS_MSG);
  }

  @Test
  void shouldProcessNumberFormatExceptionOnInvalidInputFormat() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn("NaN", EXIT);
    when(ruleService.getAll()).thenReturn(emptyList());

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(NAN_ERROR_MSG);
  }

  @Test
  void shouldPrintMessageOnNumberOutOfRulesListRange() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn("2", EXIT);
    when(ruleService.getAll()).thenReturn(singletonList(RULE));

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(OUT_OF_RANGE_ERROR_MSG + 2);
  }

  @Test
  void shouldPrintMessageOnNegativeRuleIndex() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn("-1", EXIT);
    when(ruleService.getAll()).thenReturn(singletonList(RULE));

    assertDoesNotThrow(controller::start);

    verify(ioService, times(1)).outputStringLine(OUT_OF_RANGE_ERROR_MSG + -1);
  }

  @Test
  void shouldPrintMultipleFlightsOnCorrectInput() {
    when(ioService.readStringWithPrompt(anyString())).thenReturn("1", EXIT);
    when(ruleService.getAll()).thenReturn(singletonList(RULE));
    when(filteringService.filterFights(Set.of(RULE))).thenReturn(List.of(FLIGHT, FLIGHT));

    assertDoesNotThrow(controller::start);

    verify(ioService, times(2)).outputStringLine(FLIGHT.toString());
  }
}
