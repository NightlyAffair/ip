package cheryl.util;

import cheryl.manager.Manager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the file system that handles the reading, writing, and serialization of task data. It
 * provides methods to load tasks from a file, save tasks to a file, and serialize/deserialize tasks
 * for storage and retrieval.
 *
 * @author Nithvin Leelakrishnan
 */
public class FileSystem {

  /** The file path where data are stored. */
  private final static String PATHNAME = "./data/tasks.txt";


  public FileSystem() {
  }

  /**
   * Retrieves the list of tasks from the file. If the file exists, it deserializes the tasks and
   * returns them as an ArrayList. If the file does not exist, it returns an empty list.
   *
   * @return The list of tasks read from the file, or an empty list if the file doesn't exist.
   */
  public static void read(Manager manager) {
    try {
      FileReader fr = new FileReader(PATHNAME);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        manager.read(line);
      }
      br.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  /**
   * Saves the current tasks to the file. It first ensures the directory exists, then writes the
   * serialized tasks to the specified file.
   */
  public static void write(String string) {
    try {
      Path filePath = Paths.get(PATHNAME);
      Files.createDirectories(filePath.getParent());
      Files.deleteIfExists(filePath);
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATHNAME))) {
        bw.write(string);
      }
    } catch (IOException e) {
      System.out.println("Error saving file");
    }
  }


}
