package com.gridnine.testing.controller;

public final class MenuConstants {

  public static final String PROMPT = "> ";

  public static final String LINE = "";

  public static final String ALL = "\\a";

  public static final String EXIT = "\\e";

  public static final String HELP = "\\h";

  public static final String USAGE = """
      Usage:
      - %s to print all flights (no rules)
      - %s to exit
      - %s to print this message
      Enter space-separated numbers to apply one or more rules
      Example: "%s1 2" to apply first and second rules"""
      .formatted(ALL, EXIT, HELP, PROMPT);

  public static final String EMPTY_FLIGHTS_MSG = "No flights present for current set of rules";

  public static final String OUT_OF_RANGE_ERROR_MSG = "ERROR: Number is out of range: ";

  public static final String NAN_ERROR_MSG = "ERROR: Not a number";

  public static final String RULE_PRINT_FORMAT = "%d) %s";

  private MenuConstants() {
  }
}
