package View.CLI;


import View.CLI.Command.Command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<String, Command> commands;

    public TextMenu() {
        commands = new LinkedHashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command c : commands.values()) {
            String line = String.format("%4s : %s", c.getKey(), c.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        String key;
        Command command;

        //noinspection InfiniteLoopStatement
        while (true) {
            printMenu();
            System.out.print("Input the option: ");
            key = scanner.nextLine();
            command = commands.get(key);

            if (command == null) {
                System.out.println("Invalid option");
                continue;
            }
            command.execute();
        }
    }
}