package cheryl.util;

import cheryl.task.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchSystemTest {
  @Test
  public void findFunctionTest() {
    TaskList taskList = new TaskList();
    taskList.addTodo("borrow book");
    String actualString = SearchSystem.find("borrow", taskList.get());
    String expectedString =
        """
                Found the following tasks with that phrase:
                [T][ ] borrow book
                """;
    Assertions.assertEquals(expectedString, actualString);
  }
}
