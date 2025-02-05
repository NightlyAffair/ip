package cheryl.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cheryl.util.TaskList;
import org.junit.jupiter.api.Test;

public class TaskListTest {
  @Test
  public void todoTest() {
    String expected =
        """
                Got it. I've added this task:
                    \
                [T][ ] borrow book
                \
                Now you have 1 tasks in the list.""";

    String userInput = "borrow book";

    TaskList taskList = new TaskList();

    assertEquals(expected, taskList.addTodo(userInput));
  }

  @Test
  public void addDeadlineTest() {
    String expected =
        """
                Got it. I've added this task:
                    \
                [D][ ] return book (by: Dec/02/2019 1800)
                \
                Now you have 1 tasks in the list.""";

    String userInput = "return book /by 2/12/2019 1800";

    TaskList taskList = new TaskList();
    assertEquals(expected, taskList.addDeadline(userInput));
  }

  @Test
  public void addEventTest() {
    String expected =
        """
                Got it. I've added this task:
                    \
                [E][ ] project meeting (from: Dec/02/2019 1800 to: Dec/02/2019 2000)
                \
                Now you have 1 tasks in the list.""";

    String userInput = "project meeting /from 2/12/2019 1800 /to 2/12/2019 2000";

    TaskList taskList = new TaskList();
    assertEquals(expected, taskList.addEvent(userInput));
  }
}
