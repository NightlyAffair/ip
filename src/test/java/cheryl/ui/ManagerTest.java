package cheryl.ui;

import cheryl.manager.MainManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {
  @Test
  public void TaskTestNumber() {
    MainManager manager = new MainManager();
    String helpString = manager.run("1");
    String expected =
        """
                To track a todo: todo borrow book
                To track a deadline: deadline return book /by 2/12/2019 1800
                To track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000
                To see your tasks in the list: list
                To mark task 1 in the list as done: mark 1
                To unmark task 1 in the list as done unmark 1
                To delete task 1 in the list: delete 1
                To search tasks with book in the list: search book
                To quit: quit""";

    Assertions.assertEquals(expected, helpString);
  }

  @Test
  public void TaskTestString() {
    MainManager manager = new MainManager();
    String helpString = manager.run("task");
    String expected =
        """
                    To track a todo: todo borrow book
                    To track a deadline: deadline return book /by 2/12/2019 1800
                    To track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000
                    To see your tasks in the list: list
                    To mark task 1 in the list as done: mark 1
                    To unmark task 1 in the list as done unmark 1
                    To delete task 1 in the list: delete 1
                    To search tasks with book in the list: search book
                    To quit: quit""";

    Assertions.assertEquals(expected, helpString);
  }

  @Test
  public void ContactTestNumber() {
    MainManager manager = new MainManager();
    String contactString = manager.run("2");
    String expected =
        """
                To add: add /n name /p phone number /e emailaddress@gmail.com /a Singapore Marina Bay Sands
                To remove: remove name
                To edit a contact: edit /n name /p phone number
                To list: list
                To quit: quit""";

    Assertions.assertEquals(expected, contactString);
  }

  @Test
  public void ContactTestString() {
    MainManager manager = new MainManager();
    String contactString = manager.run("contact");
    String expected =
        """
                    To add: add /n name /p phone number /e emailaddress@gmail.com /a Singapore Marina Bay Sands
                    To remove: remove name
                    To edit a contact: edit /n name /p phone number
                    To list: list
                    To quit: quit""";

    Assertions.assertEquals(expected, contactString);
  }
}
