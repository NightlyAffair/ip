package cheryl.util;

import cheryl.exception.FileCorruptedException;
import cheryl.manager.Manager;
import cheryl.task.*;
import cheryl.task.TaskType;
import cheryl.inputproccessor.TimeProcessor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the file system that handles the reading, writing, and serialization of task data. It
 * provides methods to load tasks from a file, save tasks to a file, and serialize/deserialize tasks
 * for storage and retrieval.
 *
 * @author Nithvin Leelakrishnan
 */
public class FileSystem {

  /** The file path where data are stored. */
  private final String pathName = "./data/tasks.txt";


  public FileSystem() {
  }

  /**
   * Retrieves the list of tasks from the file. If the file exists, it deserializes the tasks and
   * returns them as an ArrayList. If the file does not exist, it returns an empty list.
   *
   * @return The list of tasks read from the file, or an empty list if the file doesn't exist.
   */
  public void read(Manager manager) {
    try {
      FileReader fr = new FileReader(pathName);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        manager.read(line);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Saves the current tasks to the file. It first ensures the directory exists, then writes the
   * serialized tasks to the specified file.
   */
  public void write(String string) {
    try {
      Files.createDirectories(Paths.get(pathName).getParent());
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
        bw.write(string);
      }
    } catch (IOException e) {
      System.out.println("Error saving file");
    }
  }


}
