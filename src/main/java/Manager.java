import java.util.ArrayList;

public class Manager {
    private ArrayList<Task> taskList;

    Manager() {
        this.taskList = new ArrayList<>();
    }

    public String run(String userInput) {
        String[] userCommand = userInput.split(" ");
        String userString = builder(userCommand);
        switch (userCommand[0]) {
            case "help":
                return helpString();
            case "todo":
                return addTodo(userString);
            case "deadline":
                break;
            case "event":
                break;
            case "list":
                return listOut();
            case "mark":
                return markList(userString);
            case "unmark":
                break;
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
        return todo.toString();
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
            stringBuilder.append(taskList.get(i).toString());
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

}
