import java.util.Scanner;

public class Cheryl {
    public static void main(String[] args) {
        printIntro();
        Scanner scn = new Scanner(System.in);
        Boolean runMainLoop = true;
        Manager manager = new Manager();
        while(runMainLoop) {
            String userInput = scn.nextLine();
            switch (userInput) {
                case "bye":
                    runMainLoop = false;
                    break;
                default:
                    System.out.println(manager.run(userInput));
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



    static void printOutro() {
        String outroLineOne = "Bye. Hope to see you again soon!";
        System.out.println(outroLineOne);
    }
}
