import java.util.ArrayList;

public class Manager {
    private ArrayList<Task> taskList;

    Manager() {
        this.taskList = new ArrayList<>();
    }

    public String run(String userInput) {
        String[] userCommand = userInput.split(" ");
        String userString = builder(userCommand);
        Commands command;

        try {
            command = Commands.valueOf(userCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Huh?? Please enter a valid input...";
        }

        switch (command) {
            case HELP:
                return helpString();
            case LIST:
                return listOut();
        }

        try {
            if (userCommand.length < 2) {
                throw new MissingInputException();
            }
            switch (command) {
                case TODO:
                    return addTodo(userString);
                case DEADLINE:
                    return addDeadline(userString);
                case EVENT:
                    return addEvent(userString);
            }

            if(Integer.parseInt(userString) < 1 || Integer.parseInt(userString) > taskList.size()) {
                throw new OutOfIndexException();
            }

            switch (command) {
                case MARK:
                    return markList(userString);
                case UNMARK:
                    return unmarkList(userString);
                case DELETE:
                    return delete(userString);
            }
        } catch (MissingInputException | OutOfIndexException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please enter a number to indicate the list item to be modified.";
        }

        return "";
    }

    private String helpString() {
        String sayTodo = "Say todo to track a todo like todo borrow book";
        String sayDeadlines = "Say deadline to track a deadline like deadline return book /by Sunday";
        String sayEvent = "Say event to track a event like event project meeting /from Mon 2pm /to 4pm";
        String sayList = "Say list to see your tasks in the list";
        String sayMark = "Say mark 1 to mark task 1 in the list as done";
        String sayUnMark = "Say unmark 1 to unmark task 1 in the list as done";
        String sayDelete = "Say delete 1 to delete task 1 in the list";
        return sayTodo + '\n' + sayDeadlines + '\n' + sayEvent + '\n' + sayList + '\n' + sayMark + '\n' + sayUnMark + '\n' + sayDelete;
    }

    private String addTodo(String userInput) {
        Todo todo = new Todo(userInput);
        this.taskList.add(todo);
        return addTaskString(todo.toString());
    }

    private String addDeadline(String userInput) {
        Deadline deadline = new Deadline(userInput);
        this.taskList.add(deadline);
        return addTaskString(deadline.toString());
    }

    private String addEvent(String userInput) {
        Event event = new Event(userInput);
        this.taskList.add(event);
        return addTaskString(event.toString());
    }

    private String builder(String[] userCommand) {
        if (userCommand.length == 1) {
            return userCommand[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < userCommand.length; i++) {
            stringBuilder.append(userCommand[i]);

            if (i != userCommand.length - 1) {
                stringBuilder.append(' ');
            }
        }

        return stringBuilder.toString();
    }
    private String listOut() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            stringBuilder.append(Integer.toString(i + 1) + '.');
            stringBuilder.append(this.taskList.get(i).toString());
            if (i != this.taskList.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    private String markList(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index).mark();
        this.taskList.set(index, task);
        return task.toString();
    }

    private String unmarkList(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index).unmark();
        this.taskList.set(index, task);
        return task.toString();
    }

    private String delete(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index);
        this.taskList.remove(task);
        return removeTaskString(task.toString());
    }

    private String addTaskString(String taskDescription) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:" + '\n' + "    ");
        stringBuilder.append(taskDescription + '\n');
        stringBuilder.append("Now you have " + this.taskList.size() + " tasks in the list.");
        return stringBuilder.toString();
    }

    private String removeTaskString(String taskDescription) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've removed this task:" + '\n' + "    ");
        stringBuilder.append(taskDescription + '\n');
        stringBuilder.append("Now you have " + this.taskList.size() + " tasks in the list.");
        return stringBuilder.toString();
    }
}
