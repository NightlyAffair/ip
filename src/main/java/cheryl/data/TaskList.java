package cheryl.data;

import cheryl.task.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String addTodo(String userInput) {
        Todo todo = new Todo(userInput);
        this.taskList.add(todo);
        return addTaskString(todo.toString());
    }

    public String addDeadline(String userInput) {
        Deadline deadline = new Deadline(userInput);
        this.taskList.add(deadline);
        return addTaskString(deadline.toString());
    }

    public String addEvent(String userInput) {
        Event event = new Event(userInput);
        this.taskList.add(event);
        return addTaskString(event.toString());
    }

    public String listOut() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            stringBuilder.append(i + 1).append('.');
            stringBuilder.append(this.taskList.get(i).toString());
            if (i != this.taskList.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    public String markList(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index).mark();
        this.taskList.set(index, task);
        return task.toString();
    }

    public String unmarkList(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index).unmark();
        this.taskList.set(index, task);
        return task.toString();
    }

    public String delete(String userInput) {
        Integer index = Integer.parseInt(userInput) - 1;
        Task task = this.taskList.get(index);
        this.taskList.remove(task);
        return removeTaskString(task.toString());
    }

    public String addTaskString(String taskDescription) {
        return "Got it. I've added this task:" + '\n' + "    " +
                taskDescription + '\n' +
                "Now you have " + this.taskList.size() + " tasks in the list.";
    }

    public String removeTaskString(String taskDescription) {
       return "Got it. I've removed this task:" + '\n' + "    " +
                taskDescription + '\n' +
               "Now you have " + this.taskList.size() + " tasks in the list.";
    }

    public ArrayList<Task> get() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }
}
