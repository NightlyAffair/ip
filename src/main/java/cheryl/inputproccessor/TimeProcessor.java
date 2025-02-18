package cheryl.inputproccessor;

import cheryl.ui.TaskUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeProcessor {
  private LocalDateTime dateTime;

  public TimeProcessor(String dateTime) {
    try {
      // Sample String: 2/12/2019 1800
      DateTimeFormatter formatter;
      if (dateTime.contains(" ")) {
        formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
      } else {
        formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        this.dateTime = LocalDateTime.of(LocalDate.parse(dateTime, formatter), LocalTime.MIDNIGHT);
      }

    } catch (DateTimeParseException e) {
      System.out.println(
          "Invalid date format: "
              + dateTime
              + "\n please provide date and time again in the following format: 2/12/2019 1800");
      this.dateTime = new TimeProcessor(Parser.scan()).dateTime;
    }
  }

  public TimeProcessor(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }
}
