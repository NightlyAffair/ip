package cheryl.util;

import cheryl.exception.FileCorruptedException;
import cheryl.task.*;
import cheryl.ui.TaskType;
import cheryl.ui.TimeProcessor;
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
  private final String pathName = "./data/tasks.txt";

  /** The list of tasks managed by the file system. */
  private final ArrayList<Task> tasks;

  /** Constructs a new FileSystem with an empty list of tasks. */
  public FileSystem() {
    tasks = new ArrayList<>();
  }

  /**
   * Constructs a new FileSystem with the specified list of tasks.
   *
   * @param tasks The list of tasks to initialize the file system with.
   */
  public FileSystem(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Retrieves the list of tasks from the file. If the file exists, it deserializes the tasks and
   * returns them as an ArrayList. If the file does not exist, it returns an empty list.
   *
   * @return The list of tasks read from the file, or an empty list if the file doesn't exist.
   */
  public ArrayList<Task> getTasks() {
    try {
      FileReader fr = new FileReader(pathName);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        tasks.add(deserialize(line));
      }
    } catch (IOException e) {
      return tasks;
    }
    assert !tasks.isEmpty();
    return tasks;
  }

  /**
   * Saves the current tasks to the file. It first ensures the directory exists, then writes the
   * serialized tasks to the specified file.
   */
  public void pushFile() {
    try {
      Files.createDirectories(Paths.get(pathName).getParent());
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
        bw.write(serialize());
      }
    } catch (IOException e) {
      System.out.println("Error saving file");
    }
  }

  /**
   * Serializes the list of tasks into a string format suitable for storage. Each task is serialized
   * on a new line.
   *
   * @return A string representing all tasks, serialized for storage.
   */
  public String serialize() {
    StringBuilder sb = new StringBuilder();
    for (Task task : tasks) {
      sb.append(task.serialize());
      sb.append("\n");
    }

    return sb.toString();
  }

  /**
   * Deserializes a string representing a task into a Task object. The string is split into
   * components based on the "||" separator and the appropriate task type is created.
   *
   * @param line The serialized string representing a task.
   * @return The corresponding Task object, or null if the task type is unrecognized.
   * @throws FileCorruptedException If the task type in the serialized string is invalid or
   *     corrupted.
   */
  public Task deserialize(String line) {
    String[] details = line.split("\\|\\|");
    try {
      switch (TaskType.valueOf(details[0])) {
        case TASK -> {
          return new Task(details[1], details[2]);
        }
        case TODO -> {
          return new Todo(details[1], details[2]);
        }
        case DEADLINE -> {
          return new Deadline(
              new Task(details[1], details[2]), new TimeProcessor(details[3]).getDateTime());
        }
        case EVENT -> {
          return new Event(
              details[1],
              details[2],
              new TimeProcessor(details[3]).getDateTime(),
              new TimeProcessor(details[4]).getDateTime());
        }
        default -> throw new FileCorruptedException();
      }
    } catch (FileCorruptedException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
