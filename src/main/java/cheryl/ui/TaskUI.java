package cheryl.ui;

import java.util.Scanner;

public class TaskUI {

  public static void printIntroString() {
    System.out.println("Please enter your Task");
  }

  public static String helpString() {
    String sayTodo = "Say todo to track a todo like todo borrow book";
    String sayDeadlines =
            "Say deadline to track a deadline like deadline return book /by 2/12/2019 1800";
    String sayEvent =
            "Say event to track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000";
    String sayList = "Say list to see your tasks in the list";
    String sayMark = "Say mark 1 to mark task 1 in the list as done";
    String sayUnMark = "Say unmark 1 to unmark task 1 in the list as done";
    String sayDelete = "Say delete 1 to delete task 1 in the list";
    String sayExit = "Say 0 to quit";
    return sayTodo
            + '\n'
            + sayDeadlines
            + '\n'
            + sayEvent
            + '\n'
            + sayList
            + '\n'
            + sayMark
            + '\n'
            + sayUnMark
            + '\n'
            + sayDelete
            + '\n'
            + sayExit;
  }
}
