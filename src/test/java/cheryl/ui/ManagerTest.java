package cheryl.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    @Test
    public void helpStringTest() {
        Manager manager = new Manager();
        String helpString = manager.run("help");
        String expected = """
                Say todo to track a todo like todo borrow book
                Say deadline to track a deadline like deadline return book /by 2/12/2019 1800
                Say event to track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000
                Say list to see your tasks in the list
                Say mark 1 to mark task 1 in the list as done
                Say unmark 1 to unmark task 1 in the list as done
                Say delete 1 to delete task 1 in the list""";

        Assertions.assertEquals(expected, helpString);
    }

    @Test
    public void taskCommandTest() {
        Manager manager = new Manager();
        String taskString = manager.run("todo borrow book");
        String expected = """
                Got it. I've added this task:
                    \
                [T][ ] borrow book
                \
                Now you have 1 tasks in the list.""";

        Assertions.assertEquals(expected, taskString);
    }
}
