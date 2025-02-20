package cheryl.ui;

import java.util.Scanner;

public class TaskUI {

  public static void printIntroString() {
    System.out.println("Please enter your Task");
  }

  public static String helpString() {
    String sayTodo = "To track a todo: todo borrow book";
    String sayDeadlines =
            "To track a deadline: deadline return book /by 2/12/2019 1800";
    String sayEvent =
            "To track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000";
    String sayList = "To see your tasks in the list: list";
    String sayMark = "To mark task 1 in the list as done: mark 1";
    String sayUnMark = "To unmark task 1 in the list as done unmark 1";
    String sayDelete = "To delete task 1 in the list: delete 1";
    String sayExit = "To quit: quit";
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
