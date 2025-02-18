package cheryl.ui;

import cheryl.manager.MainManager;

public class MainUI {
    public static void printIntro() {
        String introLineOne = "Hello! I'm Cheryl";
        String introLineTwo = "What can I do for you?";
        System.out.println(introLineOne);
        System.out.println(introLineTwo);
    }

    public static void printOptions() {
        System.out.println(MainManager.options());
    }

    public static void printOutro() {
        String outroLineOne = "Bye. Hope to see you again soon!";
        System.out.println(outroLineOne);
    }
}
