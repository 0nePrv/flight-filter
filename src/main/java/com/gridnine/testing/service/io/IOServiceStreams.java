package com.gridnine.testing.service.io;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class IOServiceStreams implements IOService {

  private final PrintStream output;

  private final Scanner input;

  public IOServiceStreams(PrintStream outputStream, InputStream inputStream) {
    output = outputStream;
    input = new Scanner(inputStream);
  }

  @Override
  public void outputStringLine(String s) {
    output.println(s);
  }

  @Override
  public void outputString(String s) {
    output.print(s);
  }

  @Override
  public String readStringWithPrompt(String prompt) {
    outputString(prompt);
    return input.nextLine();
  }
}
