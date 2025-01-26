import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileSystem {
    private final String pathName = "./data/tasks.txt";
    private ArrayList<Task> tasks;

    FileSystem() {
        tasks = new ArrayList<>();
    }

    FileSystem(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public ArrayList<Task> getTasks() {
        //If file exists get file and de-serialize it
        try {
            FileReader fr = new FileReader(pathName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                tasks.add(deserialize(line));
            }
        //If file doesn't exist pass empty arraylist back to main
        } catch (IOException e) {
            return tasks;
        }
        return tasks;
    }

    //Write file to disk
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

    //Serialize file before writing
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.serialize());
            sb.append("\n");
        }

        return sb.toString();
    }

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
                    return new Deadline(new Task(details[1], details[2]), new TimeProcessor(details[3]).getDateTime());
                }
                case EVENT -> {
                    return new Event(details[1], details[2], new TimeProcessor(details[3]).getDateTime(), new TimeProcessor(details[4]).getDateTime());
                }
                default -> {
                    throw new FileCorruptedException();
                }
            }
        } catch (FileCorruptedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
