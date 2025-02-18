package cheryl.ui;

import cheryl.main.DataList;
import cheryl.task.Task;
import cheryl.util.FileSystem;
import cheryl.util.TaskList;
import java.util.ArrayList;

public class Manager {
  private final DataList dataList;

  public Manager() {
    dataList = new DataList();
  }

  public Manager(DataList dataList) {
    this.dataList = dataList;
  }

  public String run(String userInput) {
    return UserCommand.run(userInput, dataList);
  }

  public void pushFile() {
    new FileSystem(dataList).pushFile();
  }
}
