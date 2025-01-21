import java.util.Scanner;

public class Cheryl {
    public static void main(String[] args) {
        printIntro();
        Scanner scn = new Scanner(System.in);
        Boolean runMainLoop = true;
        while(runMainLoop) {
            String userInput = scn.nextLine();
            switch (userInput) {
                case "help":
                    printHelp();
                    break;
                case "todo":
                    break;
                case "deadline":
                    break;
                case "event":
                    break;
                case "list":
                    break;
                case "mark"
                case "bye":
                    runMainLoop = false;
                    break;
            }
        }
        printOutro();
    }

    static void printIntro() {
        String introLineOne = "Hello! I'm Cheryl";
        String introLineTwo = "What can I do for you?";
        String introLineThree = "Say help if you don't know what you are doing";
        System.out.println(introLineOne);
        System.out.println(introLineTwo);
        System.out.println(introLineThree);
    }

    static void printHelp() {
        String sayTodo = "Say todo to track a todo like todo borrow book";
        String sayDeadlines = "Say deadline to track a deadline like deadline return book /by Sunday";
        String sayEvent = "Say event to track a event like event project meeting /from Mon 2pm /to 4pm";
        String sayList = "Say list to see your tasks in the list";
        String sayMark = "Say mark 1 to mark task 1 in the list as done";
        String sayUnMark = "Say unmark 1 to unmark task 1 in the list as done";
        String sayDelete = "Say delete 1 to delete task 1 in the list";
        System.out.println(sayTodo);
        System.out.println(sayDeadlines);
        System.out.println(sayEvent);
        System.out.println(sayList);
        System.out.println(sayMark);
        System.out.println(sayUnMark);
        System.out.println(sayDelete);
    }

    static void printOutro() {
        String outroLineOne = "Bye. Hope to see you again soon!";
        System.out.println(outroLineOne);
    }
}
