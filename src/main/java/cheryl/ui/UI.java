package cheryl.ui;

import java.util.Scanner;

public class UI {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printIntro() {
        String introLineOne = "Hello! I'm cheryl.main.Cheryl";
        String introLineTwo = "What can I do for you?";
        String introLineThree = "Say help if you don't know what you are doing";
        System.out.println(introLineOne);
        System.out.println(introLineTwo);
        System.out.println(introLineThree);
    }

    public static void printOutro() {
        String outroLineOne = "Bye. Hope to see you again soon!";
        System.out.println(outroLineOne);
    }

    public static String scan() {
        return SCANNER.nextLine();
    }

    static String helpString() {
        String sayTodo = "Say todo to track a todo like todo borrow book";
        String sayDeadlines = "Say deadline to track a deadline like deadline return book /by 2/12/2019 1800";
        String sayEvent = "Say event to track a event like event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000";
        String sayList = "Say list to see your tasks in the list";
        String sayMark = "Say mark 1 to mark task 1 in the list as done";
        String sayUnMark = "Say unmark 1 to unmark task 1 in the list as done";
        String sayDelete = "Say delete 1 to delete task 1 in the list";
        return sayTodo + '\n' + sayDeadlines + '\n' + sayEvent + '\n' + sayList + '\n' + sayMark + '\n' + sayUnMark + '\n' + sayDelete;
    }
}
