package cheryl.util;

import cheryl.exception.FileCorruptedException;
import cheryl.main.Data;
import cheryl.main.DataList;
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

  /** The file path where tasks are stored. */
  private final static String PATHNAME = "./data/tasks.txt";

  /** The list of data managed by the file system. */
  private final DataList dataList;

  /** Constructs a new FileSystem with an empty list of tasks. */
  public FileSystem() {
    this.dataList = new DataList();
  }

  /**
   * Constructs a new FileSystem with the specified list of tasks.
   *
   * @param dataList The list of data to initialize the file system with.
   */
  public FileSystem(DataList dataList) {
    this.dataList = dataList;
  }

  /**
   * Retrieves the list of data from the file. If the file exists, it deserializes the data and
   * returns them as an DataList. If the file does not exist, it returns an empty list.
   *
   * @return The list of tasks read from the file, or an empty list if the file doesn't exist.
   */
  public DataList getTasks() {
    try {
      FileReader fr = new FileReader(PATHNAME);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        dataList.add(dataList.deserialize(line));
      }
    } catch (IOException e) {
      return dataList;
    }
    assert !dataList.isEmpty();
    return dataList;
  }

  /**
   * Saves the current tasks to the file. It first ensures the directory exists, then writes the
   * serialized tasks to the specified file.
   */
  public void pushFile() {
    try {
      Files.createDirectories(Paths.get(PATHNAME).getParent());
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATHNAME))) {
        bw.write(dataList.serialize());
      }
    } catch (IOException e) {
      System.out.println("Error saving file");
    }
  }
}