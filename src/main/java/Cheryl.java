import cheryl.FileSystem;
import cheryl.ui.Manager;
import cheryl.ui.UI;

public class Cheryl {
    private final Manager manager;

    Cheryl() {
        this.manager = new Manager(new FileSystem().getTasks());
    }

    public void run() {
        UI.printIntro();
        Boolean runMainLoop = true;
        while(runMainLoop) {
            String userInput = UI.scan();
            if (userInput.equals("bye")) {
                runMainLoop = false;
            } else {
                System.out.println(manager.run(userInput));
            }
        }
        manager.pushFile();
        UI.printOutro();
    }
    public static void main(String[] args) {
        new Cheryl().run();
    }
}
