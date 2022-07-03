package hwr.oop;

import java.io.PrintStream;
import java.util.Scanner;

public class InputUse {

    private Scanner scanner;

    public InputUse() {
        this.scanner = new Scanner(System.in);
    }

    public String enterTaskName() {
        System.out.println("Enter a task: ");
        String task = scanner.nextLine();
        System.out.println("you've entered: " + task);
        return task;
    }

    public String enterDate() {
        System.out.println("Enter a date for the task: ");
        String date = scanner.nextLine();
        System.out.println("you've entered: " + date);
        return date;
    }

    public String enterDeadline() {
        System.out.println("Enter a deadline for the task: ");
        String date = scanner.nextLine();
        System.out.println("you've entered: " + date);
        return date;
    }
}
