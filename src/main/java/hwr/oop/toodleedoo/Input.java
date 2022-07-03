package hwr.oop.toodleedoo;

import java.io.*;
import java.util.Scanner;

public class Input {
    private Scanner scanner;
    private PrintStream printStream;

    public Input(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    //TODO: Zweiter Konstruktor, damit es tatsächlich nutzbar ist
    public Input() {

    }

    public void enterTaskName() {
        printStream.println("Enter a task: ");
        String task = scanner.nextLine();
        printStream.println("output: " + task);
    }

    public void enterDate() {
        printStream.println("Enter a date for the task: ");
        String date = scanner.nextLine();
        printStream.println("output: " + date);
    }

    public void enterDeadline() {
        printStream.println("Enter a deadline for the task: ");
        String date = scanner.nextLine();
        printStream.println("output: " + date);
    }
}
