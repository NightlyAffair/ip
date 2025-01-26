public class Parser {
    //Parses out details from user input
    static String details(String[] userCommand) {
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
}
